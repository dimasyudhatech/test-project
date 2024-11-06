package com.ny.spring.controller;

import com.ny.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity get(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size) {
        // Add guard clause to check if page or size is less than or equal to 0
        if (page <= 0 || size <= 0) {
            return ResponseEntity.badRequest().body("Page and size must be greater than 0");
        }

        return ResponseEntity.ok(testService.get(page, size));
    }
}