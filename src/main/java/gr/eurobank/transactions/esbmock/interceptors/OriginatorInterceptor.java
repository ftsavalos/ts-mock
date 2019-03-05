package gr.eurobank.transactions.esbmock.interceptors;

import gr.eurobank.transactions.esbmock.handler.PathVariableNullityException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class OriginatorInterceptor extends HandlerInterceptorAdapter {

    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> pathVariables = ((Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));

        List<Map.Entry<String, String>> nullEntries = pathVariables.entrySet().stream().filter(x -> x.getValue().equals("null")).collect(toList());

        if(!nullEntries.isEmpty())
            throw new PathVariableNullityException("Path variable [" + nullEntries.get(0).getKey() + "] sent from State Machine is null");

        return super.preHandle(request, response, handler);
    }

}
