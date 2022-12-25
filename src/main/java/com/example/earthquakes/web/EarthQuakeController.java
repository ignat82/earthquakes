package com.example.earthquakes.web;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.service.EarthQuakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import static com.example.earthquakes.Constants.EARTHQUAKE_TEMPLATE;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EarthQuakeController {
    private final EarthQuakeService earthQuakeService;

    @GetMapping("/earthquake")
    public String showForm(EarthQuakeModel earthQuakeModel) {
        return "earthquake";
    }

    @PostMapping("/earthquake")
    public String processForm(EarthQuakeModel earthQuakeModel) {
        //C:\Users\KDFX Modes\Documents\java\earthquakes\src\main\resources\data\nov20quakedatasmall.atom
        log.info("model data received {}", earthQuakeModel);
        Optional<List<QuakeEntry>> quakeEntries
                = earthQuakeService.getEntriesFromFile(earthQuakeModel.getSource());
        if (quakeEntries.isEmpty()) {
            earthQuakeModel.setMessage("failed to get entries from source");
            return EARTHQUAKE_TEMPLATE;
        }
        log.info("entries are:");
        quakeEntries.get().forEach(e -> log.info(String.valueOf(e)));


        return EARTHQUAKE_TEMPLATE;
    }
}
