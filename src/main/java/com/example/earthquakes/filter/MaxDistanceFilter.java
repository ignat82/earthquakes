package com.example.earthquakes.filter;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxDistanceFilter implements Filter {
    private final Location location;
    private final double maxDistance;
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }
}
