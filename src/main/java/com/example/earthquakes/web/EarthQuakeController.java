package com.example.earthquakes.web;

import com.example.earthquakes.EarthQuakeParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URL;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EarthQuakeController {
    private final EarthQuakeParser earthQuakeParser;

    @GetMapping("/earthquake")
    public String showForm(EarthQuakeModel earthQuakeModel) {
        return "earthquake";
    }

    @PostMapping("/earthquake")
    public String processForm(EarthQuakeModel earthQuakeModel) {
        //C:\Users\KDFX Modes\Documents\java\earthquakes\src\main\resources\data\nov20quakedatasmall.atom
        log.info("model data received {}", earthQuakeModel);
        String templateName = "earthquake";
        String path = null;
        try {
            URL res = getClass().getClassLoader().getResource(earthQuakeModel.getSource());
            path = Paths.get(res.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            String message = "failed to acquire absolute patch";
            earthQuakeModel.setMessage(message);
            return templateName;
        }
        log.info("got path {}", path);
        earthQuakeParser.read(path).forEach(e -> log.info(String.valueOf(e)));
        return templateName;
    }
}
