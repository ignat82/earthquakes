package com.example.earthquakes.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.EarthQuakeParser;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class WebAdapter {
    private final EarthQuakeParser earthQuakeParser;
    private final EarthQuakeClient earthQuakeClient;
    //private final ClosestQuakes closestQuakes;
    private Optional<ArrayList<QuakeEntry>> quakeEntries = Optional.empty();

    public Optional<List<QuakeEntry>> getEntriesFromFile(String relativePath) {
        String path;
        try {
            URL res = getClass().getClassLoader().getResource(relativePath);
            path = Paths.get(res.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            log.error("failed to acquire absolute patch");
            return Optional.empty();
        }
        log.info("got path {}", path);
        List<QuakeEntry> quakeEntries = earthQuakeParser.read(path);
        log.info("got entries");
        return Optional.ofNullable(quakeEntries);
    }

    public Optional<List<QuakeEntry>> filterByDistance(Location location, double dist) {
        return quakeEntries.map(q -> earthQuakeClient.filterByDistanceFrom(q, dist, location));
    }

    public Optional<List<QuakeEntry>> filterByMagnitude(String magMin) {
        if (quakeEntries.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                earthQuakeClient.filterByMagnitude(
                        quakeEntries.get(), Double.parseDouble(magMin)));
    }
}
