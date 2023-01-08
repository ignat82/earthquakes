package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MathAllFilter extends AbstractFilter implements Filter {
    private final List<Filter> filters = new ArrayList<>();

    public MathAllFilter() {
        super(MathAllFilter.class.getName());
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    public boolean satisfies(QuakeEntry qe) {
        return filters.stream().allMatch(f -> f.satisfies(qe));
    }

    @Override
    public String getName() {
        return "applied filters :\n" +
                filters.stream()
                .map(Filter::getName)
                .collect(Collectors.joining(""));
    }
}
