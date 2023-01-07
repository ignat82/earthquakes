package com.example.earthquakes;

import com.example.earthquakes.entities.Location;
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

    public ArrayList<QuakeEntry> filterByClosestTo(long howMany,
                                                   Location from) {
        ArrayList<QuakeEntry> quakeData = deepCopy(quakeEntries).orElseGet(ArrayList::new);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        if (quakeData.isEmpty()) {
            return new ArrayList<>();
        }

        while (howMany-- > 0 && !quakeData.isEmpty()) {
            QuakeEntry nearestQuake
                    = Collections.min(quakeData, (q1, q2) -> Float.compare(
                            q1.getLocation().distanceTo(from),
                            q2.getLocation().distanceTo(from)));
            answer.add(nearestQuake);
            log.info("added with maxDistance {}", nearestQuake.getLocation().distanceTo(from));
            quakeData.remove(nearestQuake);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByLargest(long howMany) {
        ArrayList<QuakeEntry> quakeData = deepCopy(quakeEntries).orElseGet(ArrayList::new);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        if (quakeData.isEmpty()) {
            return new ArrayList<>();
        }
        while (howMany-- > 0 && !quakeData.isEmpty()) {
            QuakeEntry largestQuake = Collections.max(
                    quakeData, Comparator.comparingDouble(QuakeEntry::getMagnitude));
            answer.add(largestQuake);
            log.info("added with magmituge {}", largestQuake.getMagnitude());
            quakeData.remove(largestQuake);
        }
        return answer;
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

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
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

