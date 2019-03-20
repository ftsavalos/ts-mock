package gr.eurobank.transactions.esbmock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("{} to: {}", request.getMethod(), this.getPartialURI(request.getRequestURI()));
    }

    private String getPartialURI(String uri) {
        String[] uriSegments = uri.split("/", 4);
        return uriSegments[uriSegments.length - 1];
    }
}
