package com.example.functionalRestWebflux.router;

import com.example.functionalRestWebflux.handler.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieRouter {

    @Bean
    public RouterFunction<ServerResponse> movieRoutes(MovieHandler movieHandler) {
        /*return route()
                .GET("/hello", serverRequest -> ServerResponse.ok().bodyValue("Hello World"))
                .GET("/movies", serverRequest -> movieHandler.getAllMovies())
                .GET("/movies/{id}", serverRequest -> movieHandler.getMovieById(serverRequest))
                .PUT("/movies/{id}", movieHandler::updateMovie)
                .POST("/movies", movieHandler::addMovie)
                .build();*/
        return route().nest(RequestPredicates.path("/movies"), builder -> builder
                .GET("", serverRequest -> movieHandler.getAllMovies())
                .GET("/{id}", serverRequest -> movieHandler.getMovieById(serverRequest))
                .PUT("/{id}", movieHandler::updateMovie)
                .POST("", movieHandler::addMovie)
                .GET("livestream", movieHandler::streamMovies))
                .GET("/hello", serverRequest -> ServerResponse.ok().bodyValue("Hello World"))
                .build();
    }
}
