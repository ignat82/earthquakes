package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractForm {
    private boolean formInvalid;
    private boolean entriesPresent;
}
