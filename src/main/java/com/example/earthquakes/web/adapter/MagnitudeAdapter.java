package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MagnitudeFilter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MagnitudeForm;
import org.springframework.stereotype.Component;

@Component
public class MagnitudeAdapter extends AbstractAdapter {

    public MagnitudeAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new MagnitudeFilter((MagnitudeForm) form);
    }
}
