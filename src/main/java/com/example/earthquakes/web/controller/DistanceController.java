package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.DistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.DISTANCE_TEMPLATE;

@Controller
public class DistanceController extends AbstractController {
    public DistanceController(WebAdapter webAdapter) {
        super(webAdapter, DISTANCE_TEMPLATE);
    }

    @GetMapping("/earthquake/distance")
    public String doGet(DistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping("/earthquake/distance")
    public String doPost(DistanceForm form) {
        return super.doPost(form, webAdapter.filterByDistance(form.getLatitude(),
                                                              form.getLongitude(),
                                                              form.getMinDistance(),
                                                              form.getMaxDistance()));
    }
}
