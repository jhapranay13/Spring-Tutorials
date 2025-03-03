package com.example.springWebFlux.activity;

import com.example.springWebFlux.entities.Movie;
import com.example.springWebFlux.service.MovieService;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/movie/mf")
public class MovieActivityMonoFluxActivity {

    MovieService movieService;
    Sinks.Many<Movie> movieSink = Sinks.many().replay().all();

    public MovieActivityMonoFluxActivity(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("{id}")
    public Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("all")
    public Flux<Movie> getAllMovie() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Movie> streamAllMovie() {
        return movieService.getAllMovies().delayElements(Duration.ofMillis(500)).log();
    }

    @PostMapping
    public Mono<Movie> addMovie(@RequestBody @Valid Movie movie) {
        return movieService.addMovie(movie).log()
                .doOnNext(savedMovie -> movieSink.tryEmitNext(savedMovie)); //Emit the value to the sink
    }
//    @GetMapping(value = "livestream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    @GetMapping(value = "livestream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Movie> streamLiveMovieUpdate() {
        return movieSink.asFlux().log();
    }
}
