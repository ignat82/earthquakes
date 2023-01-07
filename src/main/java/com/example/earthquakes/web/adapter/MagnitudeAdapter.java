package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MagnitudeFilter;
import com.example.earthquakes.web.formdata.MagnitudeForm;

public class MagnitudeAdapter extends AbstractAdapter {

    public MagnitudeAdapter(EarthQuakeClient earthQuakeClient,
                            MagnitudeForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter() {
        return new MagnitudeFilter((MagnitudeForm) form);
    }
}
