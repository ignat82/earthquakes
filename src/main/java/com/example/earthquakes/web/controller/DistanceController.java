package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.DistanceAdapter;
import com.example.earthquakes.web.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.DistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class DistanceController extends AbstractController {
    private final String PATH = DISTANCE_PATH;

    public DistanceController(WebAdapter webAdapter, EarthQuakeClient earthQuakeClient) {
        super(webAdapter, earthQuakeClient, DISTANCE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(DistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(DistanceForm form) {
        DistanceAdapter adapter = new DistanceAdapter(earthQuakeClient, form);
        return super.doPost(form, adapter.filterBy(form));
    }
}
