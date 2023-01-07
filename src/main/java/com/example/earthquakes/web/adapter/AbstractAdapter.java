package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.filter.AbstractFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.AbstractForm;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbstractAdapter {
    final EarthQuakeClient earthQuakeClient;

    public Optional<List<QuakeEntry>> filterBy(AbstractForm form) {
        try {
            return earthQuakeClient.getFilteredEntries(initializeFilter());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Filter initializeFilter() {
        return new AbstractFilter();
    }
}
