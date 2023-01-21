package com.example.earthquakes.web.adapter.single.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MaxDistanceFilter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MaxDistanceForm;
import org.springframework.stereotype.Component;

@Component
public class MaxDistanceAdapter extends AbstractFilterAdapter {

    public MaxDistanceAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new MaxDistanceFilter((MaxDistanceForm) form);
    }
}
