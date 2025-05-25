package org.server.calendarjpa.config;

import org.server.calendarjpa.common.filter.SessionAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionFilterConfig {
    @Bean
    public FilterRegistrationBean<SessionAuthFilter> sessionFilterRegistration() {
        FilterRegistrationBean<SessionAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SessionAuthFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
