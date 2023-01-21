package com.example.earthquakes.web.adapter.single.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.comparator.DepthComparator;
import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.DeepestForm;
import org.springframework.stereotype.Component;

@Component
public class DeepestAdapter extends AbstractComparatorAdapter {

    public DeepestAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public EntriesComparator initializeComparator(AbstractForm abstractForm) {
        setHowMany(Integer.parseInt(((DeepestForm) abstractForm).getHowMany()));
        return new DepthComparator((DeepestForm) abstractForm);
    }
}
