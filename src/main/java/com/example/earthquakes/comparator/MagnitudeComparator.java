package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.LargestForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MagnitudeComparator implements EntriesComparator {

    private final String name;

    public MagnitudeComparator(LargestForm magnitudeForm) {
        name = MagnitudeComparator.class.getName();
    }

    public int compare(QuakeEntry e1, QuakeEntry e2) {
        return Double.compare(e2.getMagnitude(), e1.getMagnitude());
    }

    public String getName() {
        return name;
    }
}
