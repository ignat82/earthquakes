package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistanceFilter {
    private String latitude;
    private String longitude;
    private String distance;
    private boolean formInvalid;
    private boolean entriesPresent;

}
