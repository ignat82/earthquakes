package com.example.earthquakes.sorting;

import com.example.earthquakes.EarthQuakeParser;
import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class QuakeSortWithTwoArrayLists implements QuakeSort {
    // This is the code from the Video of Selection Sort with Two ArrayLists

    public QuakeSortWithTwoArrayLists() {
        // TODO Auto-generated constructor stub
    }

    public List<QuakeEntry> sortBy(List<QuakeEntry> quakeEntries,
                                   int howMany,
                                   EntriesComparator quakeEntryComparator) {
        log.info("started sort by with\n {} entries, \n {} howMany, \n {} comparator",
                 quakeEntries.size(), howMany, quakeEntryComparator.getName());
        ArrayList<QuakeEntry> out = new ArrayList<>();
        while(howMany-- > 0 && !quakeEntries.isEmpty()) {
            QuakeEntry minElement = getSmallest(quakeEntries, quakeEntryComparator);
            quakeEntries.remove(minElement);
            out.add(minElement);

        }
        return out;
    }

    public QuakeEntry getSmallest(List<QuakeEntry> quakeEntries,
                                  EntriesComparator entriesComparator) {
        QuakeEntry min = quakeEntries.get(0);
        for (QuakeEntry q: quakeEntries) {
            if (entriesComparator.compare(q, min) < 0) {
                min = q;
            }
        }

        return min;
    }

    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }

        return min;
    }

    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while(!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }

        return out;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");
        list = sortByMagnitude(list);

        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
       // String source = "data/nov20quakedatasmall.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }

	}

}
