package com.example.api_gateway.congif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeBuilder){

        return routeBuilder.routes()
                .route("userService", route -> route.path("/api/auth/**")
                        .uri("lb://user-service"))
                .route("userService", route -> route.path("/api/users/**")
                        .filters(filter -> filter.filter(jwtAuthenticationFilter))
                        .uri("lb://user-service"))
                .route("productService", route -> route.path("/api/products/**")
                        .filters(filter -> filter.filter(jwtAuthenticationFilter))
                        .uri("lb://product-service"))
                .route("orderService", route -> route.path("/api/orders/**")
                        .filters(filter -> filter.filter(jwtAuthenticationFilter))
                        .uri("lb://order-service"))
                .build();

    }
}
