package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.MaxDistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class MaxDistanceController extends AbstractController {
    private final String PATH = MAX_DISTANCE_PATH;

    public MaxDistanceController(WebAdapter webAdapter) {
        super(webAdapter,  MAX_DISTANCE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MaxDistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MaxDistanceForm form) {
        return super.doPost(form, webAdapter.filterByMaxDistance(form.getLatitude(),
                                                                 form.getLongitude(),
                                                                 form.getMaxDistance()));
    }
}
