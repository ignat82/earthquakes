package com.example.earthquakes.comparator;

import com.example.earthquakes.entities.QuakeEntry;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyComparator implements EntriesComparator {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        return 0;
    }
    public String getName() {
        return (EntriesComparator.class.getName());
    }
}
