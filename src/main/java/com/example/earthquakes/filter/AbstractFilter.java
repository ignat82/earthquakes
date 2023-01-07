package com.example.earthquakes.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractFilter {
    private final String name;
}
