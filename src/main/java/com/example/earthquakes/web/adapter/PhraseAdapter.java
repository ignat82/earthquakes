package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.PhraseFilter;
import com.example.earthquakes.web.formdata.PhraseForm;

public class PhraseAdapter extends AbstractAdapter {
    public PhraseAdapter(EarthQuakeClient earthQuakeClient,
                         PhraseForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter() {
        return new PhraseFilter((PhraseForm) form);
    }
}
