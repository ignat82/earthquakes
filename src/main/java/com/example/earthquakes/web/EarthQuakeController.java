package com.example.earthquakes.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class EarthQuakeController {
    @PostMapping("/earthquake")
    public String processForm(EarthQuakeModel earthQuakeModel) {
        log.info("model data {}", earthQuakeModel);
        return "earthquake";
    }

    @GetMapping("/earthquake")
    public String showForm(EarthQuakeModel earthQuakeModel) {
        return "earthquake";
    }
}
