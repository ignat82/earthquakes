package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entries {
    private String source;
    private boolean entriesPresent;

    public String toString() {
        return String.format("source - %s, entries present - %s",
                             source, entriesPresent);
    }
}
