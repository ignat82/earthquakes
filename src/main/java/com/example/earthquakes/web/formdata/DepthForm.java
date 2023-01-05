package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepthForm extends AbstractForm {
    private String minDepth;
    private String maxDepth;
}
