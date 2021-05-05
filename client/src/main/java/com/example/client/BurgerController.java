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
        return Burger.builder()
                .pieceOfBread(pieceOfBread)
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
}
