package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.ClosestForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DistanceComparator implements EntriesComparator {
    private final String name;
    private final Location from;

    public DistanceComparator(ClosestForm form) {
        name = DistanceComparator.class.getName();
        from = new Location(Double.parseDouble(form.getLatitude()),
                            Double.parseDouble(form.getLongitude()));
    }
    public int compare(QuakeEntry e1, QuakeEntry e2) {
        return Float.compare(
                e1.getLocation().distanceTo(from),
                e2.getLocation().distanceTo(from));
    }

    public String getName() {
        return name;
    }
}
