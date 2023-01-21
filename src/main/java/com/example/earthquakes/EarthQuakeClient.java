package com.example.earthquakes;

import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.earthquakes.entities.Constants.SOURCE_FILE_LOCATION;

@Component
@Slf4j
public class EarthQuakeClient {
    private final EarthQuakeParser earthQuakeParser;
    @Getter
    @Setter
    private Optional<ArrayList<QuakeEntry>> quakeEntries;
    public EarthQuakeClient(EarthQuakeParser earthQuakeParser) {
        this.earthQuakeParser = earthQuakeParser;
        quakeEntries = getEntriesFromFile(SOURCE_FILE_LOCATION);
    }

    public Optional<List<QuakeEntry>> getFilteredEntries(Filter filter) {
        return quakeEntries.map(entries -> entries.stream()
                                                  .filter(filter::satisfies)
                                                  .collect(Collectors.toCollection(ArrayList::new)));
    }

    public Optional<List<QuakeEntry>> getSortedBy(long howMany,
                                                  EntriesComparator quakeEntryComparator) {
        log.info("sorting with comparator {}", quakeEntryComparator.getName());
        List<QuakeEntry> quakeData
                = deepCopy(quakeEntries).orElseGet(ArrayList::new);
        log.info("quake data has {} entries", quakeData.size());
        List<QuakeEntry> sortedEntries = new ArrayList<>();
        while (howMany-- > 0 && !quakeData.isEmpty()) {
            QuakeEntry nearestQuake = Collections.min(quakeData, quakeEntryComparator);
            log.info("got min entry - {}", nearestQuake.toString());
            sortedEntries.add(nearestQuake);
            quakeData.remove(nearestQuake);
        }
        return Optional.of(sortedEntries);
    }

    Optional<List<QuakeEntry>> getAllSortedBy(EntriesComparator quakeEntryComparator) {
        return getSortedBy(quakeEntries.get().size(), quakeEntryComparator);
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

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,MinMagForm,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    Optional<ArrayList<QuakeEntry>> deepCopy(Optional<ArrayList<QuakeEntry>> source) {
        return  Optional.of(source.orElseGet(ArrayList::new).stream()
                                  .map(QuakeEntry::copy)
                                  .collect(Collectors.toCollection(ArrayList::new)));
    }
}

