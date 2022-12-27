package com.example.earthquakes.web;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.DistanceFilter;
import com.example.earthquakes.web.formdata.Entries;
import com.example.earthquakes.web.formdata.MagnitudeFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.earthquakes.Constants.CLOSEST_TEMPLATE;
import static com.example.earthquakes.Constants.EARTHQUAKE_TEMPLATE;
import static com.example.earthquakes.Constants.MAGNITUDE_TEMPLATE;

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
        Optional<List<QuakeEntry>> parcedQuakeEntries
                = webAdapter.getEntriesFromFile(form.getSource());
        if (parcedQuakeEntries.isEmpty() && webAdapter.getQuakeEntries().isEmpty()) {
            return EARTHQUAKE_TEMPLATE;
        } else
            parcedQuakeEntries.ifPresent(quakeEntries -> webAdapter.setQuakeEntries(Optional.of(
                    quakeEntries.stream()
                                .map(QuakeEntry::copy)
                                .collect(Collectors.toCollection(ArrayList::new)))));
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("entries set are:");
        webAdapter.getQuakeEntries().get().forEach(e -> log.info(e.toString()));
        return EARTHQUAKE_TEMPLATE;
    }

    @GetMapping("/earthquake/closest")
    public String getClosest(DistanceFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return CLOSEST_TEMPLATE;
    }

    @PostMapping("/earthquake/closest")
    public String postClosest(DistanceFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries = Optional.empty();
        try {
            Double.parseDouble(form.getLatitude());
            Double.parseDouble(form.getLongitude());
            filteredQuakeEntries = webAdapter.filterByMagnitude(form.getMagnitude());
        } catch (Exception e) {
            form.setFormInvalid(true);
        }

        if (filteredQuakeEntries.isPresent()) {
            //form.setFormInvalid(false);
            filteredQuakeEntries.stream().forEach(e -> log.info(e.toString()));
        }
        return CLOSEST_TEMPLATE;
    }

    @GetMapping("/earthquake/magnitude")
    public String getClosest(MagnitudeFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return MAGNITUDE_TEMPLATE;
    }

    @PostMapping("/earthquake/magnitude")
    public String postClosest(MagnitudeFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries = Optional.empty();
        try {
            Double.parseDouble(form.getMagnitude());
            filteredQuakeEntries = webAdapter.filterByMagnitude(form.getMagnitude());
        } catch (Exception e) {
            form.setFormInvalid(true);
        }

        if (filteredQuakeEntries.isPresent()) {
            //form.setFormInvalid(false);
            filteredQuakeEntries.stream().forEach(e -> log.info(e.toString()));
        }
        return MAGNITUDE_TEMPLATE;
    }
}
