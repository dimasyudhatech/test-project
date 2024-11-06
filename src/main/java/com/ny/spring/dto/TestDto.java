package com.ny.spring.dto;

import lombok.Data;

@Data
public class TestDto {
    private int id;
    private String title;

    private TestDto(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TestDto create(int id, String title) {
        return new TestDto(id, title);
    }
}
