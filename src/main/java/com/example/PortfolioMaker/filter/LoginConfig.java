package com.example.PortfolioMaker.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class LoginConfig {
    @Bean
    public FilterRegistrationBean LoginFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean((Filter) new LoginFilter());
        bean.addUrlPatterns("/","/userEdit/*"
//                ,"/reservation/*","/hotelDetail/*","/reservationDetail/*"
                );
        bean.setOrder(0);
        return bean;
    }
}
