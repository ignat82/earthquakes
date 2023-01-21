package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DistanceComparator implements EntriesComparator {
    private final Location from;
    public int compare(QuakeEntry e1, QuakeEntry e2) {
        return Float.compare(
                e1.getLocation().distanceTo(from),
                e2.getLocation().distanceTo(from));
    }

    public String getName() {
        return "distance from comparator";
    }
}
