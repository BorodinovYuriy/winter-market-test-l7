package ru.gb.wintermarket.auth.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
//@ActiveProfiles("test")//application-test.yaml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void test() {
        String s = webTestClient.get()
                .uri("/test")               //        /winter-market-auth
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        log.warn(s+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Assertions.assertEquals("this is a test", s);
    }
}