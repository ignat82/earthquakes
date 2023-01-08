package com.example.earthquakes.filter;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.DistanceForm;

public class DistanceFilter extends AbstractFilter implements Filter {
    private final Location location;
    private final double minDistance;
    private final double maxDistance;

    public DistanceFilter(DistanceForm form) {
        super(DistanceFilter.class.getName());
        location = new Location(Double.parseDouble(form.getLatitude()),
                                Double.parseDouble(form.getLongitude()));
        maxDistance = Double.parseDouble(form.getMaxDistance());
        minDistance = Double.parseDouble(form.getMinDistance());
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) >= minDistance
                && qe.getLocation().distanceTo(location) <= maxDistance;
    }
}
