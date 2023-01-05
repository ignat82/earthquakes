package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

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

        public static PhrasePosition fromString(String phraseString) {
            return (phraseString == null)
                    ? null
                    : Arrays.stream(PhrasePosition.ALL_VALUES)
                         .filter(v -> v.getString().equals(phraseString))
                         .findAny().orElse(PhrasePosition.ANY);
        }
    }
}
