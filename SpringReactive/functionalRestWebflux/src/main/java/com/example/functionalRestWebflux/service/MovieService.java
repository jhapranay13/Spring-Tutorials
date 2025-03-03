package com.example.functionalRestWebflux.service;

import com.example.functionalRestWebflux.entities.Movie;
import com.example.functionalRestWebflux.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovieService {

    MovieRepository movieRepository;

    public Mono<Movie> getMovieById(String id) {
        return movieRepository.getMovieById(id);
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
