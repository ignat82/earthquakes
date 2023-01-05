package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

@RequiredArgsConstructor
public class MagFilter implements Filter {
    private final double magMin;
    private final double magMax;

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }

}
