package com.example.functionalRestWebflux.globalexceptionhandler;

import jakarta.el.PropertyNotFoundException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        var errorMessage = response.bufferFactory().wrap(throwable.getMessage().getBytes());

        if (throwable instanceof IllegalArgumentException) {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return response.writeWith(Mono.just(errorMessage));
        }

        if (throwable instanceof PropertyNotFoundException) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response.writeWith(Mono.just(errorMessage));
        }
        return response.writeWith(Mono.just(errorMessage));
    }
}
