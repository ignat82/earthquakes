package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.Entries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.earthquakes.entities.Constants.*;

@Slf4j
@Controller
public class EntriesController extends AbstractController {
    private final String PATH = EARTHQUAKE_PATH;

    public EntriesController(WebAdapter webAdapter, EarthQuakeClient earthQuakeClient) {
        super(webAdapter, earthQuakeClient, EARTHQUAKE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(Entries form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(Entries form) {
        log.info("form data received {}", form);
        Optional<ArrayList<QuakeEntry>> parcedQuakeEntries
                = earthQuakeClient.getEntriesFromFile(form.getSource());
        parcedQuakeEntries.ifPresent(q -> earthQuakeClient.setQuakeEntries(Optional.of(
                q.stream().map(QuakeEntry::copy)
                 .collect(Collectors.toCollection(ArrayList::new)))));
        form.setFormInvalid(parcedQuakeEntries.isEmpty());
        form.setEntriesPresent(earthQuakeClient.getQuakeEntries().isPresent());
        earthQuakeClient.getQuakeEntries().ifPresent(q -> q.forEach(e -> log.info(e.toString())));
        form.setOutput(parcedQuakeEntries.orElseGet(ArrayList::new).stream()
                                     .map(Object::toString)
                                     .collect(Collectors.joining("\n")));
        form.setNumber(parcedQuakeEntries.orElseGet(ArrayList::new).size());
        return EARTHQUAKE_TEMPLATE;
    }
}
