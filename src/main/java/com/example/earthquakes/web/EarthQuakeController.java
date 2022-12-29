package com.example.earthquakes.web;

import com.example.earthquakes.adapter.WebAdapter;
import com.example.earthquakes.entities.QuakeEntry;
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

import static com.example.earthquakes.Constants.*;

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
        Optional<List<QuakeEntry>> filteredQuakeEntries
                    = webAdapter.filterByDistance(form.getLatitude(),
                                                  form.getLongitude(),
                                                  form.getDistance());
        form.setFormInvalid(filteredQuakeEntries.isEmpty());
        filteredQuakeEntries.ifPresent(e -> log.info(e.toString()));
        return CLOSEST_TEMPLATE;
    }

    @GetMapping("/earthquake/magnitude")
    public String getStrongest(MagnitudeFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return MAGNITUDE_TEMPLATE;
    }

    @PostMapping("/earthquake/magnitude")
    public String postStrongest(MagnitudeFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByMagnitude(form.getMagnitude());
        form.setFormInvalid(filteredQuakeEntries.isEmpty());
        filteredQuakeEntries.ifPresent(e -> log.info(e.toString()));
        return MAGNITUDE_TEMPLATE;
    }

    @GetMapping("/earthquake/depth")
    public String getDepth(DepthFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return DEPTH_TEMPLATE;
    }

    @PostMapping("/earthquake/depth")
    public String postDepth(DepthFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByDepth(form.getMinDepth(), form.getMaxDepth());
        filteredQuakeEntries.ifPresent(e -> log.info(e.toString()));
        return DEPTH_TEMPLATE;
    }

    @GetMapping("/earthquake/name")
    public String getDepth(NameFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        return NAME_TEMPLATE;
    }

    @PostMapping("/earthquake/name")
    public String postDepth(NameFilter form) {
        form.setEntriesPresent(webAdapter.getQuakeEntries().isPresent());
        log.info("form data received {}", form);
        Optional<List<QuakeEntry>> filteredQuakeEntries
                = webAdapter.filterByPhrase(form.getPhrase(), form.getPosition());
        filteredQuakeEntries.ifPresent(e -> log.info(e.toString()));
        return NAME_TEMPLATE;
    }
}
