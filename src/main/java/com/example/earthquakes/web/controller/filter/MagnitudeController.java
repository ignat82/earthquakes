package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.filter.MagnitudeAdapter;
import com.example.earthquakes.web.formdata.MagnitudeForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MAGNITUDE_PATH;
import static com.example.earthquakes.entities.Constants.MAGNITUDE_TEMPLATE;

@Controller
public class MagnitudeController extends AbstractFilterController {
    private final String PATH = MAGNITUDE_PATH;

    public MagnitudeController(EarthQuakeClient earthQuakeClient,
                               MagnitudeAdapter adapter) {
        super(earthQuakeClient, adapter, MAGNITUDE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MagnitudeForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MagnitudeForm form) {
        return super.doPost(form);
    }
}
