package gr.eurobank.transactions.esbmock.controller.transactions;

import gr.eurobank.transactions.esbmock.model.loan.dto.GetOverdueDebtResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UFMD {

    private final RestTemplate restTemplate;

    @Value("${endpoint.esb.host}${endpoint.esb.ufmd}")
    private String UFMDUrl;

    public UFMD(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("over-due")
    public List<Data> getOverdueDebtResponseResponses(@RequestBody String[] loanAccountsToBeClosed) {

        List<Data> data = Arrays.stream(loanAccountsToBeClosed)
                .map(loan -> new Data(loan, null))
                .collect(Collectors.toList());

        data.forEach(datum -> {
                    try {
                        datum.setGetOverdueDebtResponse(restTemplate.exchange(UFMDUrl, HttpMethod.GET, null, new ParameterizedTypeReference<GetOverdueDebtResponse>() {}, "nfs", datum.getLoanAccountToBeClosed()).getBody());
                    } catch (Exception e) {
                        log.info(datum.getLoanAccountToBeClosed() + " is closed");
                        datum.setGetOverdueDebtResponse(null);
                    }
                }
        );

        List<Data> finalData = data.stream()
                .filter(datum -> datum.getGetOverdueDebtResponse() != null)
                .filter(datum -> datum.getGetOverdueDebtResponse().getNewLegal().compareTo(BigDecimal.ZERO) > 0)
//                .filter(datum -> datum.getGetOverdueDebtResponse().getTotalGRD().compareTo(BigDecimal.ZERO) > 0)
//                .filter(datum -> datum.getGetOverdueDebtResponse().getTotalLoanCur().compareTo(BigDecimal.ZERO) > 0);
                .collect(Collectors.toList());

        return finalData;
    }
}

@Slf4j
@AllArgsConstructor
@lombok.Data
class Data {

    private String loanAccountToBeClosed;
    private GetOverdueDebtResponse getOverdueDebtResponse;

}
