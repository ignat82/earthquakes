package com.example.earthquakes.web;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.EarthQuakeParser;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.filter.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.earthquakes.entities.Constants.SOURCE_FILE_LOCATION;
import static com.example.earthquakes.web.formdata.PhraseForm.PhrasePosition;

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
            DepthFilter filter = new DepthFilter(Double.parseDouble(minDepth),
                                                 Double.parseDouble(maxDepth));
            return earthQuakeClient.getFilteredEntries(quakeEntries, filter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByMaxDistance(String latitude,
                                                       String longitude,
                                                       String maxDistance) {
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            MaxDistanceFilter filter = new MaxDistanceFilter(new Location(lat, lon),
                                                             Double.parseDouble(maxDistance));
            return earthQuakeClient.getFilteredEntries(quakeEntries, filter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByDistance(String latitude,
                                                       String longitude,
                                                       String minDistance,
                                                       String maxDistance) {
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            DistanceFilter filter = new DistanceFilter(new Location(lat, lon),
                                                             Double.parseDouble(minDistance),
                                                             Double.parseDouble(maxDistance));
            return earthQuakeClient.getFilteredEntries(quakeEntries, filter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByMinMagnitude(String magMin) {
        try {
            MinMagFilter minMagFilter = new MinMagFilter(Double.parseDouble(magMin));
            return earthQuakeClient.getFilteredEntries(quakeEntries, minMagFilter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByMagnitude(String magMin, String magMax) {
        try {
            MagFilter magFilter = new MagFilter(Double.parseDouble(magMin),
                                              Double.parseDouble(magMax));
            return earthQuakeClient.getFilteredEntries(quakeEntries, magFilter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByPhrase(String phrase,
                                                     PhrasePosition position) {
        try {
            PhraseFilter phraseFilter = new PhraseFilter(position, phrase);
            return earthQuakeClient.getFilteredEntries(quakeEntries, phraseFilter);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByClosest(String lattitude,
                                                       String longituge,
                                                       String number) {
        try {
            double lat = Double.parseDouble(lattitude);
            double lon = Double.parseDouble(longituge);
            long numb = Long.parseLong(number);
            Location location = new Location(lat, lon);
            return deepCopy(quakeEntries)
                    .map(q -> earthQuakeClient.filterByClosestTo(q, numb, location));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<QuakeEntry>> filterByLargest(String number) {
        try {
            long numb = Long.parseLong(number);
            return deepCopy(quakeEntries)
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