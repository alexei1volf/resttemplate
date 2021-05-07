package com.example.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/burger", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BurgerController {

    private final WebClient breadClient;
    private final WebClient baconClient;
    private final WebClient cheeseClient;

    @Autowired
    public BurgerController(WebClient.Builder webClientBuilder) {
        this.breadClient = webClientBuilder
                .baseUrl("http://localhost:8082")
                .build();
        this.baconClient = webClientBuilder
                .baseUrl("http://localhost:8083")
                .build();
        this.cheeseClient = webClientBuilder
                .baseUrl("http://localhost:8084")
                .build();
    }

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET)
    public Mono<Burger> getBurger() {
        Mono<PieceOfBread> bread = getPiesOfBread().subscribeOn(Schedulers.boundedElastic());
        Mono<Bacon> bacon = getBacon().subscribeOn(Schedulers.boundedElastic());
        Mono<Cheese> cheese = getCheese().subscribeOn(Schedulers.boundedElastic());

        return Mono.zip(bread, bacon, cheese)
                .map((a) -> new Burger(a.getT1(), a.getT2(), a.getT3()));
    }

    private Mono<PieceOfBread> getPiesOfBread() {
        return breadClient.get()
                .uri("/bread")
                .retrieve()
                .bodyToMono(PieceOfBread.class);
    }

    private Mono<Bacon> getBacon() {
        return baconClient.get()
                .uri("/bacon")
                .retrieve()
                .bodyToMono(Bacon.class);
    }

    private Mono<Cheese> getCheese() {
        return cheeseClient.get()
                .uri("/cheese")
                .retrieve()
                .bodyToMono(Cheese.class);
    }
}
