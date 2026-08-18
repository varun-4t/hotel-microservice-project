package com.gateway.ApiGateway1.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("user-service", r -> r
                        .path("/users/**")
                        .uri("lb://USERSERVICE"))

                .route("hotel-service", r -> r
                        .path("/hotels/**","/staff/**")
                        .uri("lb://HOTELSERVICE"))

                .route("rating-service", r -> r
                        .path("/ratings/**")
                        .uri("lb://RATINGSERVICE"))

                .build();
    }
}