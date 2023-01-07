package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

public class EmptyFilter implements Filter {
    public EmptyFilter() {}

    public boolean satisfies(QuakeEntry qe) {
        return false;
    }
}
