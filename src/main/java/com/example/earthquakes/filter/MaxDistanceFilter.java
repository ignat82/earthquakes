package com.example.earthquakes.filter;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.MaxDistanceForm;

public class MaxDistanceFilter implements Filter {
    private final Location location;
    private final double maxDistance;

    public MaxDistanceFilter(MaxDistanceForm form) {
        super();
        location = new Location(Double.parseDouble(form.getLatitude()),
                                Double.parseDouble(form.getLongitude()));
        maxDistance = Double.parseDouble(form.getMaxDistance());
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }
}
