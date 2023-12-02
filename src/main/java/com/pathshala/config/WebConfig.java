package com.pathshala.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFilter);
//        registrationBean.addUrlPatterns("/*"); // Apply the filter to all URLs
//        return registrationBean;
//    }
}
