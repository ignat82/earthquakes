package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MaxDistanceFilter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MaxDistanceForm;

public class MaxDistanceAdapter extends AbstractAdapter {

    public MaxDistanceAdapter(EarthQuakeClient earthQuakeClient,
                              MaxDistanceForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new MaxDistanceFilter((MaxDistanceForm) form);
    }
}
