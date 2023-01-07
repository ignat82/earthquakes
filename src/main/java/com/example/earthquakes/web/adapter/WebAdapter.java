package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class WebAdapter {
    private final EarthQuakeClient earthQuakeClient;

    public Optional<List<QuakeEntry>> filterByClosest(String lattitude,
                                                       String longituge,
                                                       String number) {
        try {
            double lat = Double.parseDouble(lattitude);
            double lon = Double.parseDouble(longituge);
            long numb = Long.parseLong(number);
            Location location = new Location(lat, lon);
            return deepCopy(earthQuakeClient.getQuakeEntries())
                    .map(q -> earthQuakeClient.filterByClosestTo(q, numb, location));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByLargest(String number) {
        try {
            long numb = Long.parseLong(number);
            return deepCopy(earthQuakeClient.getQuakeEntries())
                    .map(q -> earthQuakeClient.filterByLargest(q, numb));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    Optional<ArrayList<QuakeEntry>> deepCopy(Optional<ArrayList<QuakeEntry>> source) {
        return  Optional.of(source.orElseGet(ArrayList::new).stream()
                                  .map(QuakeEntry::copy)
                 .collect(Collectors.toCollection(ArrayList::new)));
    }
}
