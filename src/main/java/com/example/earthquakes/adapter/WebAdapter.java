package com.example.earthquakes.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.EarthQuakeParser;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.earthquakes.Constants.SOURCE_FILE_LOCATION;

@Component
@Slf4j
@Getter
@Setter
public class WebAdapter {
    private final EarthQuakeParser earthQuakeParser;
    private final EarthQuakeClient earthQuakeClient;
    private Optional<ArrayList<QuakeEntry>> quakeEntries;

    public WebAdapter(EarthQuakeParser earthQuakeParser, EarthQuakeClient earthQuakeClient) {
        this.earthQuakeParser = earthQuakeParser;
        this.earthQuakeClient = earthQuakeClient;
        quakeEntries = getEntriesFromFile(SOURCE_FILE_LOCATION);
    }

    public Optional<ArrayList<QuakeEntry>> getEntriesFromFile(String relativePath) {
        try {
            URL res = getClass().getClassLoader().getResource(relativePath);
            String path = Paths.get(res.toURI()).toFile().getAbsolutePath();
            log.info("got path {}", path);
            ArrayList<QuakeEntry> quakeEntries = earthQuakeParser.read(path);
            log.info("got entries");
            return Optional.of(quakeEntries);
        } catch (Exception e) {
            log.error("failed to acquire absolute patch. exception is {}", e.toString());
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByDepth(String minDepth,
                                                    String maxDepth) {
        try {
            double min = Double.parseDouble(minDepth);
            double max = Double.parseDouble(maxDepth);
            return quakeEntries
                    .map(q -> earthQuakeClient.filterByDepth(q, min, max));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByDistance(String lattitude,
                                                       String longituge,
                                                       String distance) {
        try {
            double lat = Double.parseDouble(lattitude);
            double lon = Double.parseDouble(longituge);
            double dist = Double.parseDouble(distance);
            Location location = new Location(lat, lon);
            return quakeEntries
                    .map(q -> earthQuakeClient.filterByDistanceFrom(q, dist, location));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByMagnitude(String magMin) {
        try {
            return quakeEntries
                    .map(q -> earthQuakeClient.filterByMagnitude(q, Double.parseDouble(magMin)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
