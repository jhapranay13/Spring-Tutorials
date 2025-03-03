package com.example.springRestClient.service;

import com.example.springRestClient.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
public class CallService {

    @Value("${webclient.baseUrl}")
    private String baseUrl;

    WebClient webClient;

    public CallService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Movie> getById(String id) {
        return webClient.get().uri(baseUrl + "/" + id)
                .retrieve()
                .onStatus(HttpStatus -> HttpStatus.is5xxServerError(),
                        clientResponse -> {
                    log.info("5xx error: {}", clientResponse.statusCode());
                    if (clientResponse.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))
                        return Mono.error(new RuntimeException("5xx error"));

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(error -> Mono.error(new RuntimeException(error)));
                })
                .bodyToMono(Movie.class)
                //.retry(3)
                .retryWhen(Retry.fixedDelay(3, java.time.Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof RuntimeException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new RuntimeException("Retries exhausted")))
                .log();
    }

    public Flux<Movie> getAll() {
        return webClient.get().uri(baseUrl + "/all")
                .retrieve()
                .bodyToFlux(Movie.class).log();
    }
}
