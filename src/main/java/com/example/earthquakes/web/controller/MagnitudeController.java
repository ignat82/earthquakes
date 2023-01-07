package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.MagForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class MagnitudeController extends AbstractController {
    private final String PATH = MAGNITUDE_PATH;

    public MagnitudeController(WebAdapter webAdapter, EarthQuakeClient earthQuakeClient) {
        super(webAdapter, earthQuakeClient, DEPTH_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MagForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MagForm form) {
        return super.doPost(form, webAdapter.filterByMagnitude(form.getMinMag(),
                                                               form.getMaxMag()));
    }
}
