package com.example.earthquakes.web.adapter.single.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DepthFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.DepthForm;
import org.springframework.stereotype.Component;

@Component
public class DepthAdapter extends AbstractFilterAdapter {

    public DepthAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new DepthFilter((DepthForm) form);
    }
}
