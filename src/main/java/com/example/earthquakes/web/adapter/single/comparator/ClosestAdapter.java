package com.example.earthquakes.web.adapter.single.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.comparator.DistanceComparator;
import com.example.earthquakes.comparator.EntriesComparator;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.ClosestForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClosestAdapter extends AbstractComparatorAdapter {

    public ClosestAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public EntriesComparator initializeComparator(AbstractForm abstractForm) {
        log.info("ClosestAdapter.initializeComparator()");
        ClosestForm closestForm = (ClosestForm) abstractForm;
        log.info("got form {}", abstractForm.toString());
        log.info("made form {}", closestForm.toString());
        setHowMany(Integer.parseInt(closestForm.getHowMany()));
        return new DistanceComparator((ClosestForm) abstractForm);
    }
}
