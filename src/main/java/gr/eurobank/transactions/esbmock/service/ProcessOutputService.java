//package gr.eurobank.transactions.esbmock.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.PathNotFoundException;
//import gr.eurobank.transactions.esbmock.model.entity.History;
//import gr.eurobank.transactions.esbmock.repository.HistoryRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class ProcessOutputService {
//
//    private final ObjectMapper mapper;
//    private final HistoryRepository historyRepository;
//    private final ProcessOutputServiceHelper processOutputServiceHelper;
//
//    public ProcessOutputService(ObjectMapper mapper, HistoryRepository historyRepository, ProcessOutputServiceHelper processOutputServiceHelper) {
//        this.mapper = mapper;
//        this.historyRepository = historyRepository;
//        this.processOutputServiceHelper = processOutputServiceHelper;
//    }
//
//    public void saveHistory(String processOutput) throws JsonProcessingException {
//        String flow = JsonPath.read(processOutput, "$.data.processId.flow");
//        String originator = JsonPath.read(processOutput, "$.data.processId.originator");
//        String requestId = JsonPath.read(processOutput, "$.data.processId.requestId");
//        Object flowError = null;
//
//        String runHistory = processOutputServiceHelper.getHistory(flow, originator, requestId);
//        String inputOriginator = processOutputServiceHelper.getInputOriginator(runHistory);
//
//        boolean scenarioSuccess = false;
//
//        try {
//            flowError = JsonPath.read(processOutput, "$.data.transactionRequestError");
//        } catch (PathNotFoundException e) {
//            log.debug("scenario has been completed successfully");
//            scenarioSuccess = true;
//        }
//        String flowErrorStr = mapper.writeValueAsString(flowError);
//        History history = new History(requestId, scenarioSuccess, flow, inputOriginator, processOutput, !processOutputServiceHelper.isMock(runHistory), flowErrorStr, mapper.writeValueAsString(processOutputServiceHelper.extractESBServiceName(flowErrorStr)));
//
//        historyRepository.save(history);
//        log.info("{}", history);
//    }
//}