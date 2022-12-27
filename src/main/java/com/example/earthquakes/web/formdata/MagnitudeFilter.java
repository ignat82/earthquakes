package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MagnitudeFilter {
    private String magnitude;
    private boolean formInvalid;
    private boolean entriesPresent;
}
