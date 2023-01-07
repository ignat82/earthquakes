package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DepthFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.DepthForm;

public class DepthAdapter extends AbstractAdapter {

    public DepthAdapter(EarthQuakeClient earthQuakeClient,
                        DepthForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new DepthFilter((DepthForm) form);
    }
}
