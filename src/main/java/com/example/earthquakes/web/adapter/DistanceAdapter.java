package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DistanceFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.DistanceForm;

public class DistanceAdapter extends AbstractAdapter {

    public DistanceAdapter(EarthQuakeClient earthQuakeClient,
                           DistanceForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter() {
        return new DistanceFilter((DistanceForm) form);
    }
}
