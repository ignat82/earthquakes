package com.example.earthquakes.web.adapter.multi;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.*;
import com.example.earthquakes.web.adapter.AbstractAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MagPhraseMaxDistForm;
import org.springframework.stereotype.Component;

@Component
public class MagPhraseMaxDistAdapter extends AbstractAdapter {
    public MagPhraseMaxDistAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm abstractForm) {
        MagPhraseMaxDistForm form = (MagPhraseMaxDistForm) abstractForm;
        MathAllFilter mathAllFilter = new MathAllFilter();
        mathAllFilter.addFilter(new MagnitudeFilter(form.getMinMag(), form.getMaxMag()));
        mathAllFilter.addFilter(new PhraseFilter(form.getPosition(), form.getPhrase()));
        mathAllFilter.addFilter(new MaxDistanceFilter(form.getLatitude(),
                                                      form.getLongitude(),
                                                      form.getMaxDistance()));
        return mathAllFilter;
    }
}
