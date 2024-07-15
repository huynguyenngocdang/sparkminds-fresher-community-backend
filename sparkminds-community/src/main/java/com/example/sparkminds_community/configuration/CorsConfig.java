package com.example.sparkminds_community.configuration;

import com.example.sparkminds_community.constant.WebPathConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
     @Bean
     public CorsFilter corsFilter() {
         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
         CorsConfiguration configuration = new CorsConfiguration();
         configuration.setAllowCredentials(true);
         configuration.addAllowedOrigin(WebPathConstant.FRONTEND_PATH);
         configuration.addAllowedHeader("*");
         configuration.addAllowedMethod("*");
         source.registerCorsConfiguration("/**", configuration);
         return new CorsFilter(source);
     }
}
