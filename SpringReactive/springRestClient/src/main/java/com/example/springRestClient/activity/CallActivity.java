package com.example.springRestClient.activity;

import com.example.springRestClient.entity.Movie;
import com.example.springRestClient.service.CallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/call")
public class CallActivity {

    CallService callService;

    public CallActivity(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("{id}")
    public Mono<Movie> getById(@PathVariable String id) {
        return callService.getById(id);
    }

    @GetMapping("all")
    public Flux<Movie> getAll() {
        return callService.getAll();
    }
}
