package com.example.earthquakes.web.adapter.irregular;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.entities.Location;
import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.adapter.single.AbstractSingleFilterAdapter;
import com.example.earthquakes.web.formdata.AbstractForm;
import com.example.earthquakes.web.formdata.ClosestForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClosestAdapter extends AbstractSingleFilterAdapter {

    public ClosestAdapter(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient);
    }

    @Override
    public Optional<List<QuakeEntry>> filterBy(AbstractForm abstractForm) {
        try {
            ClosestForm form = (ClosestForm) abstractForm;
            double lat = Double.parseDouble(form.getLatitude());
            double lon = Double.parseDouble(form.getLongitude());
            long numb = Long.parseLong(form.getHowMany());
            Location location = new Location(lat, lon);
            return  Optional.of(earthQuakeClient.filterByClosestTo(numb, location));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
