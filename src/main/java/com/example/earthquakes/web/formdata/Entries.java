package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entries extends AbstractForm {
    private String source;

    public String toString() {
        return String.format("source - %s, entries present - %s",
                             source, isEntriesPresent());
    }
}
