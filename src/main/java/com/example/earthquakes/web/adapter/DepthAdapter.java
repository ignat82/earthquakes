package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DepthFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.DepthForm;

public class DepthAdapter extends AbstractAdapter {
    private final DepthForm form;

    public DepthAdapter(EarthQuakeClient earthQuakeClient,
                        DepthForm form) {
        super(earthQuakeClient);
        this.form = form;
    }

    @Override
    public Filter initializeFilter() {
        return (Filter) new DepthFilter(form);
    }
}
