package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.MinMagForm;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class MinMagFilter extends AbstractFilter implements Filter {
    private final double magMin;

    public MinMagFilter(MinMagForm minMagForm) {
        super(MinMagForm.class.getName());
        magMin = Double.parseDouble(minMagForm.getMinMag());
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

}
