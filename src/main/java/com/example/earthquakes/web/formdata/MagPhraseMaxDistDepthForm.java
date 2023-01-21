package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MagPhraseMaxDistDepthForm extends AbstractForm {
    private String minMag;
    private String maxMag;
    private String phrase;
    private PhraseForm.PhrasePosition position;
    private String latitude;
    private String longitude;
    private String maxDistance;
    private String minDepth;
    private String maxDepth;
}
