package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.PhraseForm;
import com.example.earthquakes.web.formdata.PhraseForm.PhrasePosition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PhraseFilter implements Filter {
    private final PhrasePosition position;
    private final String phrase;

    public PhraseFilter(PhraseForm phraseForm) {
        position = phraseForm.getPosition();
        phrase = phraseForm.getPhrase();
    }
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
