package com.example.earthquakes.web.adapter.single;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.DepthFilter;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.web.adapter.AbstractAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.DepthForm;
import org.springframework.stereotype.Component;

@Component
public class DepthAdapter extends AbstractAdapter {

    public DepthAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new DepthFilter((DepthForm) form);
    }
}
