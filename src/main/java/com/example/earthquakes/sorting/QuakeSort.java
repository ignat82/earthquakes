package com.example.earthquakes.sorting;

import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.entities.QuakeEntry;

import java.util.List;

public interface QuakeSort {
    List<QuakeEntry> sortBy(List<QuakeEntry> quakeEntries,
                            int howMany,
                            EntriesComparator quakeEntryComparator);
}
