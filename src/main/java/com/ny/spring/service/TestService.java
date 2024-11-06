package com.ny.spring.service;

import com.ny.spring.dto.TestDto;

import java.util.List;

public interface TestService {
    List<TestDto> get(int page, int size);
}
