package com.example.earthquakes.web.adapter.single;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MaxDistanceFilter;
import com.example.earthquakes.web.adapter.AbstractAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MaxDistanceForm;
import org.springframework.stereotype.Component;

@Component
public class MaxDistanceAdapter extends AbstractAdapter {

    public MaxDistanceAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new MaxDistanceFilter((MaxDistanceForm) form);
    }
}
