package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

import java.util.ArrayList;
import java.util.List;

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
}
