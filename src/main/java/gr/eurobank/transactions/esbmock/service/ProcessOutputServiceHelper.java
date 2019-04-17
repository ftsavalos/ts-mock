package gr.eurobank.transactions.esbmock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Strings.isNullOrEmpty;

@Slf4j
@Service
public class ProcessOutputServiceHelper {

    private final String esbSwaggerApiDocJson;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${endpoint.state-machine.history}")
    private String stateMachineHistoryUrl;

    private HashMap<String, String> pathSummaries = new HashMap<>();
    private HashMap<String, String> historyRequestMappings = new HashMap<>();

    public ProcessOutputServiceHelper(String esbSwaggerApiDocJson, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.esbSwaggerApiDocJson = esbSwaggerApiDocJson;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void postConstruct() throws JsonProcessingException {
        fillMappings();
        JSONArray jsonArrayPaths = JsonPath.read(esbSwaggerApiDocJson, "$..paths");
        String jsonPathsString = objectMapper.writeValueAsString(jsonArrayPaths.get(0));

        HashMap paths = (HashMap) jsonArrayPaths.get(0);

        paths.keySet().forEach(path -> {
            log.info("{}", path);
            try {
                Object summaryOfPath = JsonPath.read(jsonPathsString, "$." + path + "..summary");
                pathSummaries.put(path.toString(), objectMapper.writeValueAsString(summaryOfPath));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    String extractESBServiceName(String error) {

        if (isNullOrEmpty(error) || error.equals("null"))
            return null;

        String errorEndpoint = JsonPath.read(error, "$.endpoint");
        String errorTransaction = errorEndpoint.substring(errorEndpoint.lastIndexOf("/"));

        String errorTransactionKey = pathSummaries.keySet()
                .stream()
                .filter(endpoint -> endpoint.substring(endpoint.lastIndexOf("/")).equals(errorTransaction))
                .findAny()
                .get();

        return pathSummaries.get(errorTransactionKey);
    }

    boolean isMock(String runHistory) {
        List<String> esbEndpoints = JsonPath.read(runHistory, "$..auditData[?(@.endpoint != 'StateMachine')].endpoint");
        return esbEndpoints
                .stream()
                .filter(Objects::nonNull)
                .anyMatch(endpoint -> endpoint.contains("localhost"));
    }

    String getInputOriginator(String runHistory) throws JsonProcessingException {
        Object inputOriginator;
        try {
            // for flows with more than one processes
            inputOriginator = JsonPath.read(runHistory, "$[-1:].processInstanceRevisions[-1:].auditData");
        } catch (PathNotFoundException e) {
            // for flows with one process
            inputOriginator = JsonPath.read(runHistory, "$.processInstanceRevisions[-1:].auditData");
        }
        return objectMapper.writeValueAsString(inputOriginator);
    }

    String getHistory(String flow, String originator, String requestId) {
        ResponseEntity<String> historyResponse;
        try {
            historyResponse =  restTemplate.exchange(stateMachineHistoryUrl, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, historyRequestMappings.get(flow), originator, requestId);
        } catch (RestClientException e) {
            String errorMessage = MessageFormat.format("The cause might be the absence of the key-value pair in historyRequestMappings representing the flow: {0}", flow);
            throw new RestClientException(errorMessage);
        }

        return historyResponse.getBody();
    }

    /**
     *  <b>Key</b>:    Flow name as stated in FlowConstants <br>
     *  <b>Value</b>:  Flow uri segment in history controller
     */
    private void fillMappings() {
        historyRequestMappings.put("depositAccountFlow", "depositaccount");
        historyRequestMappings.put("loanAccountFlow", "loanaccount");
        historyRequestMappings.put("loanAccountAutoMotoFlow", "loanaccountautomoto");
        historyRequestMappings.put("loanAccountCLRemedialFlow", "loanaccountclremedial");
        historyRequestMappings.put("clAmortizedLoanUpdatedStatusCollectionFlow", "cl-amortizedloanpermanentdelaycollection");
        historyRequestMappings.put("clAmortizedLoanUpdatedStatusTTECollectionFlow", "cl-amortizedloanupdatedstatus-tte-collection");
        historyRequestMappings.put("clAmortizedLoanPermanentDelayCollectionFlow", "cl-amortizedloanpermanentdelaycollection");
        historyRequestMappings.put("clAmortizedLoanInDeletionCollectionFlow", "cl-amortizedloanindeletioncollection");
        historyRequestMappings.put("lowStartDenouncedFlow", "lowstartdenounced");
        historyRequestMappings.put("lowStartNonDenouncedFlow", "lowstartnondenounced");
    }
}