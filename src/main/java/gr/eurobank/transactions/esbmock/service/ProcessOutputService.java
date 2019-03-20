package gr.eurobank.transactions.esbmock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import gr.eurobank.transactions.esbmock.model.entity.History;
import gr.eurobank.transactions.esbmock.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProcessOutputService {

    private static Map<String, String> historyRequestMappings = new HashMap<>();

    static {
        historyRequestMappings.put("depositAccountFlow", "depositaccount");
        historyRequestMappings.put("loanAccountFlow", "loanaccount");
        historyRequestMappings.put("loanAccountAutoMotoFlow", "loanaccountautomoto");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");
//        historyRequestMappings.put("depositaccount", "depositAccount");

//            "depositaccount",
//            "loanaccount",
//            "loanaccountautomoto",
//            "cl-amortized-load-dpo-cash-repayment",
//            "cl-amortizedloanpermanentdelaycollection",
//            "cl-amortizedloanupdatedstatuscollection",
//            "cl-amortizedloanupdatedstatus-tte-collection",
//            "cl-amortizedloanindeletioncollection",
//            "loanaccountclremedial",
//            "loanaccountcro",
//            "lowstartdenounced",
//            "lowstartnondenounced"
    }

    private final HistoryRepository historyRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    @Value("${endpoint.state-machine.history}")
    private String stateMachineHistoryUrl;

    public ProcessOutputService(HistoryRepository historyRepository, RestTemplate restTemplate, ObjectMapper mapper) {
        this.historyRepository = historyRepository;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }


    public void saveHistory(String processOutput) throws JsonProcessingException {
        String flow = JsonPath.read(processOutput, "$.data.processId.flow");
        String originator = JsonPath.read(processOutput, "$.data.processId.originator");
        String requestId = JsonPath.read(processOutput, "$.data.processId.requestId");

        String runHistory = getHistory(flow, originator, requestId);
        String inputOriginator = getInputOriginator(runHistory);

        boolean scenarioSuccess = false;

        try {
            JsonPath.read(processOutput, "$.data.transactionRequestError");
        } catch (PathNotFoundException e) {
            log.debug("scenario has been completed successfully");
            scenarioSuccess = true;
        }
        History history = new History(requestId, scenarioSuccess, flow, inputOriginator, processOutput, !isMock(runHistory));

        historyRepository.save(history);
        log.info("{}", history);
    }

    private String getHistory(String flow, String originator, String requestId) {
        return restTemplate.exchange(
                stateMachineHistoryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                historyRequestMappings.get(flow),
                originator,
                requestId
        ).getBody();
    }

    private String getInputOriginator(String runHistory) {
        log.info("{}", runHistory);
        return JsonPath.read(runHistory, "$.processInstanceRevisions[-1:].auditData").toString();
    }

    private boolean isMock(String runHistory) {
        List<String> esbEndpoints = JsonPath.read(runHistory, "$..auditData[?(@.endpoint != 'StateMachine')].endpoint");
        return esbEndpoints.stream().anyMatch(endpoint -> endpoint.contains("localhost:3000"));
    }
}