package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.QuakeEntry;

import java.util.Comparator;

public interface EntriesComparator extends Comparator<QuakeEntry> {
    int compare(QuakeEntry e1, QuakeEntry e2);
    String getName();
    }

