package com.example.earthquakes.filter;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.MaxDistanceForm;

public class MaxDistanceFilter extends AbstractFilter implements Filter {
    private final Location location;
    private final double maxDistance;

    public MaxDistanceFilter(MaxDistanceForm form) {
        super(MaxDistanceFilter.class.getName());
        location = new Location(Double.parseDouble(form.getLatitude()),
                                Double.parseDouble(form.getLongitude()));
        maxDistance = Double.parseDouble(form.getMaxDistance());
    }

    public MaxDistanceFilter(String latitude,
                             String longitude,
                             String maxDistance) {
        super(MaxDistanceFilter.class.getName());
        location = new Location(Double.parseDouble(latitude),
                                Double.parseDouble(longitude));
        this.maxDistance = Double.parseDouble(maxDistance);
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) <= maxDistance;
    }
}
