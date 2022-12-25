package com.example.earthquakes.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarthQuakeModel {
    private String source;

    public String toString() {
        return String.format("source - %s", source);
    }
}
