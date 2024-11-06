package com.ny.spring.controller;

import com.ny.spring.dto.TestDto;
import com.ny.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/get")
    public ResponseEntity<List<TestDto>> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        if (page <= 0 || pageSize <= 0) {
            ResponseEntity.badRequest().build();
        }

        List<TestDto> posts = testService.get(page, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}