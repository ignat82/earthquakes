package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.filter.EmptyFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.AbstractForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractAdapter {
    protected final EarthQuakeClient earthQuakeClient;

    public Optional<List<QuakeEntry>> filterBy(AbstractForm form) {
        try {
            return earthQuakeClient.getFilteredEntries(initializeFilter(form));
        } catch (Exception e) {
            log.error("caught exception while filtering entries {}", e.toString());
            return Optional.empty();
        }
    }

    public String getFilterName(AbstractForm form) {
        try {
            return initializeFilter(form).getName();
        } catch (Exception e) {
            log.error("caught exception while filtering entries {}", e.toString());
            return "";
        }
    }

    public Filter initializeFilter(AbstractForm form) {
        return new EmptyFilter();
    }
}
