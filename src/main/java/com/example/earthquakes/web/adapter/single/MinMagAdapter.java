package com.example.earthquakes.web.adapter.single;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.filter.Filter;
import com.example.earthquakes.filter.MinMagFilter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.MinMagForm;
import org.springframework.stereotype.Component;

@Component
public class MinMagAdapter extends AbstractSingleFilterAdapter {
    public MinMagAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Filter initializeFilter(AbstractForm form) {
        return new MinMagFilter((MinMagForm) form);
    }
}
