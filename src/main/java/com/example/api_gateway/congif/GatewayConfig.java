package com.example.api_gateway.congif;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeBuilder){

        return routeBuilder.routes()
                .route("userService", route -> route.path("/api/users/**")
                .uri("lb://user-service"))
                .route("productService", route -> route.path("/api/products/**")
                        .uri("lb://product-service"))
                .route("orderService", route -> route.path("/api/orders/**")
                        .uri("lb://order-service"))
                .build();

    }
}
