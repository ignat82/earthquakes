package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.DeepestForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepthComparator implements EntriesComparator {

    private final String name;

    public DepthComparator(DeepestForm deepestForm) {
        name = DepthComparator.class.getName();
    }

    public int compare(QuakeEntry e1, QuakeEntry e2) {
        return Double.compare(e2.getDepth(), e1.getDepth());
    }

    public String getName() {
        return name;
    }
}
