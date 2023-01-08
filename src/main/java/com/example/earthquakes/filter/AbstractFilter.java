package com.example.earthquakes.filter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFilter {
    private final String name;

    public String getName() {
        return "applied Filter: " + name + "\n";
    }
}
