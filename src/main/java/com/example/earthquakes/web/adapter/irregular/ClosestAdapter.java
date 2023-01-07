package com.example.earthquakes.web.adapter.irregular;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClosestAdapter {
    private final EarthQuakeClient earthQuakeClient;

    public Optional<List<QuakeEntry>> filterByClosest(String latitude,
                                                      String longitude,
                                                      String number) {
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            long numb = Long.parseLong(number);
            Location location = new Location(lat, lon);
            return  Optional.of(earthQuakeClient.filterByClosestTo(numb, location));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
