package com.example.earthquakes.web.adapter.multi;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.*;
import com.example.earthquakes.web.adapter.AbstractAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MagPhraseMaxDistDepthForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MagPhraseMaxDistDepthAdapter extends AbstractAdapter {
    public MagPhraseMaxDistDepthAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm abstractForm) {
        MagPhraseMaxDistDepthForm form = (MagPhraseMaxDistDepthForm) abstractForm;
        MathAllFilter mathAllFilter = new MathAllFilter();
        mathAllFilter.addFilter(new MagnitudeFilter(form.getMinMag(), form.getMaxMag()));
        mathAllFilter.addFilter(new PhraseFilter(form.getPosition(), form.getPhrase()));
        mathAllFilter.addFilter(new MaxDistanceFilter(form.getLatitude(),
                                                      form.getLongitude(),
                                                      form.getMaxDistance()));
        mathAllFilter.addFilter(new DepthFilter(form.getMinDepth(),
                                                form.getMaxDepth()));
        return mathAllFilter;
    }
}
