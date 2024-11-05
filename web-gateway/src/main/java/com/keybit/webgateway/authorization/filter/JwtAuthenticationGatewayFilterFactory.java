package com.keybit.webgateway.authorization.filter;

import com.keybit.webgateway.authorization.jwt.JwtUtils;
import com.keybit.webgateway.authorization.jwt.TokenUser;
import com.keybit.webgateway.authorization.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    private final JwtUtils jwtUtils;

    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!containsAuthorization(request))
                return onError(response, "missing authorization header", HttpStatus.BAD_REQUEST);

            String token = extractToken(request);

            if (!jwtUtils.isValid(token))
                return onError(response, "invalid authorization header", HttpStatus.BAD_REQUEST);

            TokenUser tokenUser = jwtUtils.decode(token);

            addAuthorizationHeaders(request, tokenUser);

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerHttpResponse response, String error, HttpStatus status) {
        response.setStatusCode(status);
        return response.setComplete();
    }

    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
        String authorization = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
        return authorization.replace("Bearer", "");
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, TokenUser tokenUser) {
        request.mutate()
                .header("X-Authorization-Id", tokenUser.getId())
                .header("X-Authorization-Role", tokenUser.getRole().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(",")))
                .build();
    }
}
