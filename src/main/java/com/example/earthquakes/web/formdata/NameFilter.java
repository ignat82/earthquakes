package com.example.earthquakes.web.formdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@Setter
@ToString
public class NameFilter extends AbstractForm {
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

        public static PhrasePosition fromString(String nameString) {
            return (nameString == null)
                    ? null
                    : Arrays.stream(PhrasePosition.ALL_VALUES)
                         .filter(v -> v.getString().equals(nameString))
                         .findAny().orElse(PhrasePosition.ANY);
        }
    }
}
