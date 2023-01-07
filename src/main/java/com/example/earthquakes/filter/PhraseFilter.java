package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.PhraseForm;
import com.example.earthquakes.web.formdata.PhraseForm.PhrasePosition;


public class PhraseFilter extends AbstractFilter implements Filter {
    private final PhrasePosition position;
    private final String phrase;

    public PhraseFilter(PhraseForm phraseForm) {
        super(PhraseFilter.class.getName());
        position = phraseForm.getPosition();
        phrase = phraseForm.getPhrase();
    }

    public PhraseFilter(PhrasePosition position, String phrase) {
        super(PhraseFilter.class.getName());
        this.position = position;
        this.phrase = phrase;
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
