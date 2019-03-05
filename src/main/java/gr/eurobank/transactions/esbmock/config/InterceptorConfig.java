package gr.eurobank.transactions.esbmock.config;

import gr.eurobank.transactions.esbmock.interceptors.LoggingInterceptor;
import gr.eurobank.transactions.esbmock.interceptors.OriginatorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;
    private final OriginatorInterceptor originatorInterceptor;

    public InterceptorConfig(LoggingInterceptor loggingInterceptor, OriginatorInterceptor originatorInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.originatorInterceptor = originatorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(originatorInterceptor).addPathPatterns("/**").excludePathPatterns("/**/requestResponse");
    }
}