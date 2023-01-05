package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.Phrase.PhrasePosition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PhraseFilter implements Filter {
    private final PhrasePosition position;
    private final String phrase;
    public boolean satisfies(QuakeEntry qe) {
        switch (position) {
            case ANY -> {
                return qe.getInfo().contains(phrase);
            }
            case END -> {
                return qe.getInfo().endsWith(phrase);
            }
            case START -> {
                return qe.getInfo().startsWith(phrase);
            }
        }
        // unreachable
        return false;
    }
}
