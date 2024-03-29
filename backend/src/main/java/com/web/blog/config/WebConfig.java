package com.web.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000").allowedMethods("PUT", "DELETE", "POST", "GET", "PATCH");
                // .allowedOrigins("http://i3b201.p.ssafy.io").allowedMethods("PUT", "DELETE", "POST", "GET", "PATCH");
    }

}
