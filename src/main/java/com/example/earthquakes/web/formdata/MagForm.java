package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MagForm extends AbstractForm{
    private String minMag;
    private String maxMag;
}
