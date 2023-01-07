package com.example.earthquakes.web.adapter.irregular;

import com.example.earthquakes.EarthQuakeClient;
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
            return Optional.of(earthQuakeClient.filterByLargest(numb));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
