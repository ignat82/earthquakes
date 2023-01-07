package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.MagnitudeAdapter;
import com.example.earthquakes.web.adapter.MaxDistanceAdapter;
import com.example.earthquakes.web.formdata.MaxDistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class MaxDistanceController extends AbstractController {
    private final String PATH = MAX_DISTANCE_PATH;

    public MaxDistanceController(EarthQuakeClient earthQuakeClient,
                                 MagnitudeAdapter adapter) {
        super(earthQuakeClient, adapter, DISTANCE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MaxDistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MaxDistanceForm form) {
        MaxDistanceAdapter adapter = new MaxDistanceAdapter(earthQuakeClient);
        return super.doPost(form, adapter.filterBy(form));
    }
}
