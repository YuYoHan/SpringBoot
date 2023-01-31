package com.example.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Log4j2
@RestController
@RequestMapping("/api/server/*")
public class ServerApiController {


    @GetMapping("/naver")
    public String naver() {
        String query = "김채원"; // 여기에 나올 이름을 UTF-8로 인코딩
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
        String endcode = StandardCharsets.UTF_8.decode(buffer).toString();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/webkr.json")
                .queryParam("query", endcode)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        // 아래는 헤더를 넣기 위함
        RequestEntity<Void> req = RequestEntity
                .get((uri))
                .header("X-Naver-Client-Id", "iz8RRSuzRxJ2whN5tv2y")
                .header("X-Naver_Client-Secret", "krVpHXP5KW")
                .build();

        ResponseEntity<String > result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }
}
