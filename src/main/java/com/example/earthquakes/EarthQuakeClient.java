package com.example.earthquakes;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EarthQuakeClient {

    public Optional<List<QuakeEntry>> getFilteredEntries(Optional<ArrayList<QuakeEntry>> quakeEntries,
                                                         Filter filter) {
        return quakeEntries.map(entries -> entries.stream()
                                                  .filter(filter::satisfies)
                                                  .collect(Collectors.toCollection(ArrayList::new)));
    }

    public ArrayList<QuakeEntry> filterByClosestTo(ArrayList<QuakeEntry> quakeData,
                                                      long howMany,
                                                      Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
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

    public ArrayList<QuakeEntry> filterByLargest(ArrayList<QuakeEntry> quakeData,
                                                   long howMany) {
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
}

