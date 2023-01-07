package com.example.earthquakes.web.adapter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DistanceFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.DistanceForm;
import org.springframework.stereotype.Component;

@Component
public class DistanceAdapter extends AbstractAdapter {

    public DistanceAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new DistanceFilter((DistanceForm) form);
    }
}
