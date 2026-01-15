package com.E_Commerce.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route("order-service", r -> r
                        .path("/api/order/**")
                        .uri("lb://ORDER-SERVICE"))
                .route("eureka-web", r -> r
                        .path("/eureka/web/**")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))

                .route("eureka-api", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))
                .build();
    }
}