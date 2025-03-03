package com.example.springWebFlux.activity;

import com.example.springWebFlux.entities.Movie;
import com.example.springWebFlux.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/movie/rf")
public class ResponseEntityReactiveTypeActivity {

    MovieService movieService;

    public ResponseEntityReactiveTypeActivity(MovieService movieService) {
        this.movieService = movieService;
    }

    // This way of defining the response entity
    // the header nad status is known immediately
    // body is provided asynchronously
    @GetMapping("{id}")
    public ResponseEntity<Mono<Movie>> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    // in this all the data is provided asynchronously
    @GetMapping("all")
    public ResponseEntity<Flux<Movie>> getAllMovie() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    // Single responseEntity is only allowed so incase of flux this
    // is the way to go
    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Movie>> streamAllMovie() {
        return ResponseEntity.ok().body(movieService.getAllMovies().delayElements(Duration.ofMillis(500))
        .log());
    }

    @PostMapping
    public ResponseEntity<Mono<Movie>> addMovie(@RequestBody @Valid Movie movie) {
        return ResponseEntity.ok().body(movieService.addMovie(movie));
    }
}
