package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractForm {
    private boolean formInvalid;
    private boolean entriesPresent;
    private String output;
    private int number;
}
