package com.example.springWebFlux.service;

import com.example.springWebFlux.entities.Movie;
import com.example.springWebFlux.repository.MovieRepository;
import jakarta.el.PropertyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovieService {

    MovieRepository movieRepository;

    public Mono<Movie> getMovieById(String id) {
        return movieRepository.getMovieById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Movie not found")));
    }

    public Flux<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Mono<Movie> addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public Mono<Movie> updateMovie(String id, Movie movie) {
        return movieRepository.updateMovie(id, movie);
    }

    public Flux<Movie> getMovieByGenre(String genre) {
        return movieRepository.getMovieByGenre(genre);
    }
}
