package com.example.earthquakes.filter;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
public class DistanceFilter implements Filter {
    private final Location location;
    private final double distance;
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < distance;
    }
}
