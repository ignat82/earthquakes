package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

import java.util.ArrayList;
import java.util.List;

public class MathAllFilter implements Filter {
    private final List<Filter> filters = new ArrayList<>();
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    public boolean satisfies(QuakeEntry qe) {
        return filters.stream().allMatch(f -> f.satisfies(qe));
    }
}
