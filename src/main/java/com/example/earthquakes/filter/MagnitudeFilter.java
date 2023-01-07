package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.MagnitudeForm;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MagnitudeFilter implements Filter {
    private final double magMin;
    private final double magMax;

    public MagnitudeFilter(MagnitudeForm magnitudeForm) {
        magMin = Double.parseDouble(magnitudeForm.getMinMag());
        magMax = Double.parseDouble(magnitudeForm.getMaxMag());
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }

}
