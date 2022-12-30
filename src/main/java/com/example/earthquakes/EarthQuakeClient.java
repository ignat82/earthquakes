package com.example.earthquakes;

import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.earthquakes.web.formdata.PhraseFilter.PhrasePosition;

@Component
public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                PhrasePosition position,
                                                String phrase) {
        switch (position) {
            case ANY -> {
                return quakeData.stream()
                                .filter(q -> q.getInfo().contains(phrase))
                                .collect(Collectors.toCollection(ArrayList::new));
            }
            case END -> {
                return quakeData.stream()
                                .filter(q -> q.getInfo().endsWith(phrase))
                                .collect(Collectors.toCollection(ArrayList::new));
            }
            case START -> {
                return quakeData.stream()
                                .filter(q -> q.getInfo().startsWith(phrase))
                                .collect(Collectors.toCollection(ArrayList::new));
            }
        }
        return quakeData;
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                   double magMin ) {
        // TODO
        return quakeData.stream()
                        .filter(q -> q.getMagnitude() > magMin)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth,
                                               double maxDepth) {
        // TODO
        return quakeData.stream()
                        .filter(q -> q.getDepth() > minDepth &&
                                q.getDepth() < maxDepth)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
                                                      double distMax,
                                                      Location from) {
        // ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        return quakeData.stream()
                        .filter(e -> e.getLocation().distanceTo(from) < distMax)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes(double magMin) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = filterByMagnitude(parser.read(source), magMin);
        System.out.println("read data for "+list.size()+" quakes");

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
