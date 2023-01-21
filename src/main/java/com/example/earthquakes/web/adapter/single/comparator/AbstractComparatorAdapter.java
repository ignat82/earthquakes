package com.example.earthquakes.web.adapter.single.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.comparator.EmptyComparator;
import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.AbstractForm;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractComparatorAdapter {
    protected final EarthQuakeClient earthQuakeClient;
    @Setter
    private int howMany;

    public Optional<List<QuakeEntry>> sortBy(AbstractForm form) {
        try {
            log.info("got form {}", form.toString());
            EntriesComparator entriesComparator = initializeComparator(form);
            log.info("need to get {} sorted entries", howMany);
            return earthQuakeClient.getSortedBy(howMany, entriesComparator);
        } catch (Exception e) {
            log.error("caught exception while sorting entries {}", e.toString());
            return Optional.empty();
        }
    }

    public String getComparatorName(AbstractForm form) {
        try {
            return initializeComparator(form).getName();
        } catch (Exception e) {
            log.error("caught exception while sorting entries {}", e.toString());
            return "";
        }
    }

    public EntriesComparator initializeComparator(AbstractForm form) {
        return new EmptyComparator();
    }
}
