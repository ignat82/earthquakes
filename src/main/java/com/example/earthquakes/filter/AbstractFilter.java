package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

public class AbstractFilter implements Filter{
    public AbstractFilter() {}

    public boolean satisfies(QuakeEntry qe) {
        return false;
    }
}
