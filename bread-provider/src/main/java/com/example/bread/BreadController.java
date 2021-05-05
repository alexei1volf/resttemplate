package com.example.bread;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/bread", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BreadController {

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET)
    public PieceOfBread getBreadWithDelay() {
        Executor delayed = CompletableFuture.delayedExecutor(5L, TimeUnit.SECONDS);
        return CompletableFuture.supplyAsync(() -> new PieceOfBread(10), delayed).get();
    }
}
