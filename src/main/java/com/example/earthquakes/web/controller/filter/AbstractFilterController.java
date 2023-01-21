package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.adapter.single.filter.AbstractFilterAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractFilterController {
    final EarthQuakeClient earthQuakeClient;
    final AbstractFilterAdapter adapter;
    private final String template;

    String doGet(AbstractForm form) {
        form.setEntriesPresent(earthQuakeClient.getQuakeEntries().isPresent());
        return template;
    }

    String doPost(AbstractForm form) {
        log.info("form data received {}", form);
        form.setEntriesPresent(earthQuakeClient.getQuakeEntries().isPresent());
        Optional<List<QuakeEntry>> filteredQuakes = adapter.filterBy(form);
        form.setFormInvalid(filteredQuakes.isEmpty());
        filteredQuakes.ifPresent(e -> log.info(e.toString()));
        log.info("got {} entries", filteredQuakes.orElseGet(ArrayList::new).size());
        form.setOutput(adapter.getFilterName(form)
                       + filteredQuakes.orElseGet(ArrayList::new).stream()
                                     .map(Object::toString)
                                     .collect(Collectors.joining("\n")));
        form.setNumber(filteredQuakes.orElseGet(ArrayList::new).size());
        log.info("form output is {}", form.getOutput());
        return template;
    }
}
