package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhraseForm extends AbstractForm {
    private String phrase;
    private PhrasePosition position;

    @RequiredArgsConstructor
    @Getter
    public enum PhrasePosition {
        START("start"),
        END("end"),
        ANY("any");
        private static final PhrasePosition[] ALL_VALUES = PhrasePosition.values();
        private final String string;
    }
}
