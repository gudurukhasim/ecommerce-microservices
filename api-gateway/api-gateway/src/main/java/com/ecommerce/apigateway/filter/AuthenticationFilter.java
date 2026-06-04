package com.ecommerce.apigateway.filter;

import com.ecommerce.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends
        AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();
            System.out.println("Request path: " + path);

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Missing or invalid Authorization header");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                System.out.println("Invalid JWT token");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token);

            System.out.println("Authenticated user: " + email + " role: " + role);

            return chain.filter(exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header("X-User-Email", email)
                            .header("X-User-Role", role)
                            .build())
                    .build());
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // no config needed
    }
}