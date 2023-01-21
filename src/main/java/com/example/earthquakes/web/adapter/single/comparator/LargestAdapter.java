package com.example.earthquakes.web.adapter.single.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.comparator.MagnitudeComparator;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.LargestForm;
import org.springframework.stereotype.Component;

@Component
public class LargestAdapter extends AbstractComparatorAdapter {

    public LargestAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public EntriesComparator initializeComparator(AbstractForm abstractForm) {
        setHowMany(Integer.parseInt(((LargestForm) abstractForm).getHowMany()));
        return new MagnitudeComparator((LargestForm) abstractForm);
    }
}
