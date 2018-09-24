package gr.eurobank.transactions.esbmock.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String[] uriSegments = request.getRequestURI().split("/", 4);
        String uri = uriSegments[3];

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String loggerClass = handlerMethod.getBean().getClass().getSimpleName();

        log.info("[{}]- {} to: {}", loggerClass, request.getMethod(), uri);
    }
}
