package com.example.earthquakes.web.adapter.irregular;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LargestAdapter {
    private final EarthQuakeClient earthQuakeClient;

    public Optional<List<QuakeEntry>> filterByLargest(String number) {
        try {
            long numb = Long.parseLong(number);
            return Optional.of(earthQuakeClient.filterByLargest(numb));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
