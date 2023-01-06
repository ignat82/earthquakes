package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.MagForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MAGNITUDE_PATH;
import static com.example.earthquakes.entities.Constants.MAGNITUDE_TEMPLATE;

@Controller
public class MagnitudeController extends AbstractController {
    private final String PATH = MAGNITUDE_PATH;

    public MagnitudeController(WebAdapter webAdapter) {
        super(webAdapter, MAGNITUDE_TEMPLATE);
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
