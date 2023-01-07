package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MinMagFilter;
import com.example.earthquakes.web.formdata.MinMagForm;

public class MinMagAdapter extends AbstractAdapter {
    public MinMagAdapter(EarthQuakeClient earthQuakeClient,
                         MinMagForm form) {
        super(earthQuakeClient, form);
    }

    @Override
    public Filter initializeFilter() {
        return new MinMagFilter((MinMagForm) form);
    }
}
