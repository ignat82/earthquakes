package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.PhraseFilter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.PhraseForm;
import org.springframework.stereotype.Component;

@Component
public class PhraseAdapter extends AbstractAdapter {
    public PhraseAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new PhraseFilter((PhraseForm) form);
    }
}
