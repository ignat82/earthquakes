package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

public class EmptyFilter extends AbstractFilter implements Filter {
    public EmptyFilter() {
        super(EmptyFilter.class.getName());
    }

    public boolean satisfies(QuakeEntry qe) {
        return false;
    }
}
