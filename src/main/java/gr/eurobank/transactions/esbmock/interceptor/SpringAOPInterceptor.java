package gr.eurobank.transactions.esbmock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@EnableAspectJAutoProxy
public class SpringAOPInterceptor {

//    @Around("execution(* com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(Object))")
//    public Object interceptObjectMapper(ProceedingJoinPoint thisJoinPoint) throws Throwable {
//        log.info("It Works!!!!!!");
//        String str = (String) thisJoinPoint.proceed();
//        log.info("It works##### [String = {}]", str);
//        return str;
//    }

//    @Around("execution(* gr.eurobank.transactions.esbmock.service.ProcessOutputServiceHelper.testInterceptionOfFinalMethod(String))")
//    public String testFinalInterception(ProceedingJoinPoint thisJoinPoint) throws Throwable {
//        log.info("It Works!!!!!!");
//        String str = (String) thisJoinPoint.proceed();
//        log.info("It works##### [String = {}]", str);
//        return str;
//    }
}
