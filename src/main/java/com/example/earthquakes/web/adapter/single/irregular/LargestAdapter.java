package com.example.earthquakes.web.adapter.single.irregular;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.comparator.MagnitudeComparator;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.adapter.AbstractAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.LargestForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LargestAdapter extends AbstractAdapter {

    public LargestAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Optional<List<QuakeEntry>> filterBy(AbstractForm abstractForm) {
        try {
            LargestForm form = (LargestForm) abstractForm;
            long numb = Long.parseLong(form.getHowMany());
            return earthQuakeClient.getSortedBy(numb, new MagnitudeComparator());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
