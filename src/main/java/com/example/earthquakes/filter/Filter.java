package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;

/**
 * Write a description of interface Filter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Filter {
    boolean satisfies(QuakeEntry qe);
    String getName();
}
