package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {


    // https://openapi.naver.com/v1/search/blog.json
    // ?query=%EB%A6%AC%EB%B7%B0
    // &display=10
    // &start=1
    // &sort=sim
    @GetMapping("/naver")
    public String naver() {

        String query = "갈비집";
        //String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
            .path("/v1/search/blog.json")
            .queryParam("query", "중국집")
            .queryParam("display", "10")
            .queryParam("start", "1")
            .queryParam("sort","sim")
            .encode(Charset.forName("UTF-8"))
            .build()
            .toUri();

       log.info("uri : {}", uri);


        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
            .get(uri)
            .header("X-Naver-Client-Id", "irnnPQt_Dw1FiHAkBu0Q")
            .header("X-Naver-Client-Secret", "NE2VQDzQpH")
            .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userAge}/name/{userName}")
    public Req<User> post(
                    //HttpEntity<String> entity,
                     @RequestBody Req<User> user,
                     @PathVariable int userAge,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
                     ) {
        //log.info("req : {}", entity.getBody());
        log.info("userAge : {}, userName : {}", userAge, userName);
        log.info("authorization : {}, custom : {}", authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
            user.getResBody()
        );
        return response;

    }
}
