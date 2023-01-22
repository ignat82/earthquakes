package com.example.earthquakes.sorting;

import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.entities.QuakeEntry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuakeSortBubble implements QuakeSort {

    public List<QuakeEntry> sortBy(List<QuakeEntry> quakeEntries,
                                   int howMany,
                                   EntriesComparator quakeEntryComparator) {
        for (int i = 0; i < Math.min(howMany, quakeEntries.size()); i++) {
            onePassBubbleSort(quakeEntries, i, quakeEntryComparator);
        }
        return quakeEntries.subList(quakeEntries.size() - Math.min(howMany, quakeEntries.size()),
                                    quakeEntries.size());
    }

    private void onePassBubbleSort(List<QuakeEntry> quakeEntries,
                                   int numSorted,
                                   EntriesComparator quakeEntryComparator) {
        for (int i = 0; i < quakeEntries.size() - numSorted - 1; i++) {
            if (quakeEntryComparator.compare(
                    quakeEntries.get(i),
                    quakeEntries.get(i + 1)
            ) < 0) {
                QuakeEntry tempEntry = quakeEntries.get(i);
                quakeEntries.set(i, quakeEntries.get(i + 1));
                quakeEntries.set(i + 1, tempEntry);
            }
        }
    }

}
