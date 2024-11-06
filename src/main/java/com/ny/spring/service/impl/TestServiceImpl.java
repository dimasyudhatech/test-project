package com.ny.spring.service.impl;

import com.ny.spring.dto.TestDto;
import com.ny.spring.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    private final WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();

    public List<TestDto> get(int page, int pageSize) {
        TestDto[] postsArray = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(TestDto[].class)
                .block();


        assert postsArray != null;
        List<TestDto> allPosts = Arrays.stream(postsArray)
                .map(post -> TestDto.create(post.getId(), post.getTitle()))
                .collect(Collectors.toList());

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, allPosts.size());

        return allPosts.subList(fromIndex, toIndex);
    }
}
