package com.example.functionalRestWebflux.handler;

import com.example.functionalRestWebflux.entities.Movie;
import com.example.functionalRestWebflux.service.MovieService;
import jakarta.el.PropertyNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Set;

@Slf4j
@Component
public class MovieHandler {
    MovieService movieService;
    Validator validator;
    Sinks.Many<Movie> movieSink = Sinks.many().replay().all();


    @Autowired
    public MovieHandler(MovieService movieService, Validator validator) {
        this.validator = validator;
        this.movieService = movieService;
    }

    public Mono<ServerResponse> getMovieById(ServerRequest request) {
        var id = request.pathVariable("id");
       /* return  movieService.getMovieById(id)
                .flatMap(movie -> ServerResponse.ok().bodyValue(movie))
                .switchIfEmpty(ServerResponse.notFound().build());*/
        var movie = movieService.getMovieById(id)
                .switchIfEmpty(Mono.error(new PropertyNotFoundException("Movie not found")));
        return  movie
                .flatMap(move -> ServerResponse.ok().bodyValue(move));

    }

    public Mono<ServerResponse> getAllMovies() {
        return ServerResponse.ok().body(movieService.getAllMovies(), Movie.class);
    }

    public Mono<ServerResponse> addMovie(ServerRequest request) {
        return request.bodyToMono(Movie.class)
                .doOnNext(this::validate)
                .flatMap(movie -> movieService.addMovie(movie))
                .doOnNext(movieAdded -> movieSink.tryEmitNext(movieAdded))
                .flatMap(movie -> ServerResponse.status(HttpStatus.CREATED).bodyValue(movie));
    }

    public Mono<ServerResponse> updateMovie(ServerRequest request) {
        return request.bodyToMono(Movie.class)
                .flatMap(movie -> movieService.updateMovie(request.pathVariable("id"), movie))
                .flatMap(movie -> ServerResponse.ok().bodyValue(movie));
    }

    public Mono<ServerResponse> getMovieByGenre(String genre) {
        return ServerResponse.ok().body(movieService.getMovieByGenre(genre), Movie.class);
    }

    public void validate(Movie movie) {
        Set<ConstraintViolation<Movie>> errors = validator.validate(movie);
        log.info("ConstraintViolation: {}", errors);
        var errorMessage = errors.stream()
                .map(ConstraintViolation::getMessage)
                .reduce("", (acc, message) -> acc + message + "\n");

        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public Mono<ServerResponse> streamMovies(ServerRequest serverRequest) {
        return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_NDJSON).body(movieSink.asFlux(), Movie.class).log();
    }
}
