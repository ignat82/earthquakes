package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.MagnitudeForm;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MagnitudeFilter extends AbstractFilter implements Filter {
    private final double magMin;
    private final double magMax;

    public MagnitudeFilter(MagnitudeForm magnitudeForm) {
        super(MathAllFilter.class.getName());
        magMin = Double.parseDouble(magnitudeForm.getMinMag());
        magMax = Double.parseDouble(magnitudeForm.getMaxMag());
    }

    public MagnitudeFilter(String magMin, String magMax) {
        super(MagnitudeFilter.class.getName());
        this.magMin = Double.parseDouble(magMin);
        this.magMax = Double.parseDouble(magMax);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }

}
