package com.example.earthquakes.service;

import com.example.earthquakes.EarthQuakeParser;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EarthQuakeService {
    private final EarthQuakeParser earthQuakeParser;

    public Optional<List<QuakeEntry>> getEntriesFromFile(String relativePath) {
        String path;
        try {
            URL res = getClass().getClassLoader().getResource(relativePath);
            path = Paths.get(res.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            log.error("failed to acquire absolute patch");
            return Optional.empty();
        }
        log.info("got path {}", path);
        List<QuakeEntry> quakeEntries = earthQuakeParser.read(path);
        log.info("got entries");
        return Optional.ofNullable(quakeEntries);
    }
}
