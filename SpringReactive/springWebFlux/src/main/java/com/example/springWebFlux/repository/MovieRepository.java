package com.example.springWebFlux.repository;

import com.example.springWebFlux.entities.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    public MovieRepository() {
        movies.add(new Movie("1", "The Shawshank Redemption", "Drama"));
        movies.add(new Movie("2", "The Godfather", "Crime"));
        movies.add(new Movie("3", "The Dark Knight", "Action"));
        movies.add(new Movie("4", "The Lord of the Rings: The Return of the King", "Fantasy"));
        movies.add(new Movie("5", "Pulp Fiction", "Crime"));
    }

    public Mono<Movie> getMovieById(String id) {
        return Mono.justOrEmpty(movies.stream().filter(movie -> movie.getId().equals(id)).findFirst());
    }

    public Flux<Movie> getAllMovies() {
        return Flux.fromIterable(movies);
    }

    public Mono<Movie> addMovie(Movie movie) {
        movie.setId(String.valueOf(movies.size() + 1));
        movies.add(movie);
        return Mono.just(movie);
    }

    public Mono<Movie> updateMovie(String id, Movie movie) {
        movies.removeIf(m -> m.getId().equals(id));
        movies.add(movie);
        return Mono.just(movie);
    }

    public Flux<Movie> getMovieByGenre(String genre) {
        return Flux.fromIterable(movies.stream().filter(movie -> movie.getGenre().equalsIgnoreCase(genre)).toList());
    }
}
