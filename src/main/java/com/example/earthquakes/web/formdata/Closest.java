package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Closest extends AbstractForm {
    private String latitude;
    private String longitude;
    private String howMany;
}
