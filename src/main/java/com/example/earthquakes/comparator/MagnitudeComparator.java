package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.QuakeEntry;


public class MagnitudeComparator implements EntriesComparator {

    public int compare(QuakeEntry e1, QuakeEntry e2) {
        return Double.compare(e2.getMagnitude(), e1.getMagnitude());
    }

    public String getName() {
        return "magnitude comparator";
    }
}
