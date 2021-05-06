package com.example.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/burger", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BurgerController {

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET)
    public Burger getBurger() {
        PieceOfBread pieceOfBread = getPiesOfBread();
        Bacon bacon = getBacon();
        Cheese cheese = getCheese();
        return Burger.builder()
                .pieceOfBread(pieceOfBread)
                .bacon(bacon)
                .cheese(cheese)
                .build();
    }

    private PieceOfBread getPiesOfBread() {
        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8082)
                .path("bread")
                .build()
                .toUriString();
        return new RestTemplate().getForObject(uriString, PieceOfBread.class);
    }

    private Bacon getBacon() {
        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8083)
                .path("bacon")
                .build()
                .toUriString();
        return new RestTemplate().getForObject(uriString, Bacon.class);
    }

    private Cheese getCheese() {
        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8084)
                .path("cheese")
                .build()
                .toUriString();
        return new RestTemplate().getForObject(uriString, Cheese.class);
    }
}
