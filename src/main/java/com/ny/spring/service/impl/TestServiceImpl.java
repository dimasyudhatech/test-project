package com.ny.spring.service.impl;

import com.ny.spring.dto.TestDto;
import com.ny.spring.service.TestService;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class TestServiceImpl implements TestService {
    private final WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();

    @Override
    public List<TestDto> get(int page, int size) {

        Mono<List<TestDto>> resp = webClient.get()
                .uri("/post")
                .retrieve()
                .bodyToFlux(TestDto.class)
                .collectList();

        List<TestDto> testDtos = resp.block();

        List<TestDto> filteredData = testDtos.stream()
                .skip((page - 1) * size)
                .limit(size)
                .toList();

        return filteredData;
    }
}
