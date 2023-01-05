package com.example.earthquakes.web.controller;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.earthquakes.entities.Constants.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EarthQuakeController {
    private final WebAdapter webAdapter;

    @GetMapping("/earthquake/entries")
    public String getEntries(Entries form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return EARTHQUAKE_TEMPLATE;
    }

    @PostMapping("/earthquake/entries")
    public String postEntries(Entries form) {
        log.info("form data received {}", form);
        Optional<ArrayList<QuakeEntry>> parcedQuakeEntries
                = webAdapter.getEntriesFromFile(form.getSource());
        parcedQuakeEntries.ifPresent(q -> webAdapter.setQuakeEntries(Optional.of(
                q.stream().map(QuakeEntry::copy)
                 .collect(Collectors.toCollection(ArrayList::new)))));
        form.setFormInvalid(parcedQuakeEntries.isEmpty());
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        webAdapter.getQuakeEntries().ifPresent(q -> q.forEach(e -> log.info(e.toString())));
        form.setOutput(parcedQuakeEntries.orElseGet(ArrayList::new).stream()
                                     .map(Object::toString)
                                     .collect(Collectors.joining("\n")));
        form.setNumber(parcedQuakeEntries.orElseGet(ArrayList::new).size());
        return EARTHQUAKE_TEMPLATE;
    }

    @GetMapping("/earthquake/largest")
    public String getClosest(LargestForm form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return LARGEST_TEMPLATE;
    }

    @PostMapping("/earthquake/largest")
    public String postClosest(LargestForm form) {
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByLargest(form.getHowMany());
        populateForm(form, filteredQuakeEntries);
        return LARGEST_TEMPLATE;
    }

    @GetMapping("/earthquake/min_mag")
    public String getStrongest(MinMagForm form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return MIN_MAG_TEMPLATE;
    }

    @PostMapping("/earthquake/min_mag")
    public String postStrongest(MinMagForm form) {
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByMinMagnitude(form.getMinMag());
        populateForm(form, filteredQuakeEntries);
        return MIN_MAG_TEMPLATE;
    }

    @GetMapping("/earthquake/magnitude")
    public String getWithMagnitude(MagForm form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return MAGNITUDE_TEMPLATE;
    }

    @PostMapping("/earthquake/magnitude")
    public String postWithMagnitude(MagForm form) {
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByMagnitude(form.getMinMag(), form.getMaxMag());
        populateForm(form, filteredQuakeEntries);
        return MAGNITUDE_TEMPLATE;
    }

    @GetMapping("/earthquake/phrase")
    public String getPhrase(PhraseForm form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return PHRASE_TEMPLATE;
    }

    @PostMapping("/earthquake/phrase")
    public String postPhrase(PhraseForm form) {
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByPhrase(form.getPhrase(), form.getPosition());
        populateForm(form, filteredQuakeEntries);
        return PHRASE_TEMPLATE;
    }

    private void populateForm(AbstractForm form,
                              Optional<List<QuakeEntry>> filteredQuakes) {
        log.info("form data received {}", form);
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        form.setFormInvalid(filteredQuakes.isEmpty());
        filteredQuakes.ifPresent(e -> log.info(e.toString()));
        log.info("got {} entries", filteredQuakes.orElseGet(ArrayList::new).size());
        form.setOutput(filteredQuakes.orElseGet(ArrayList::new).stream()
                                           .map(Object::toString)
                                           .collect(Collectors.joining("\n")));
        form.setNumber(filteredQuakes.orElseGet(ArrayList::new).size());
        log.info("form output is {}", form.getOutput());
    }
}
