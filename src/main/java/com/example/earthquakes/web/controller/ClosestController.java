package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.ClosestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class ClosestController extends AbstractController {
    private final String PATH = CLOSEST_PATH;

    public ClosestController(WebAdapter webAdapter) {
        super(webAdapter, CLOSEST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(ClosestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(ClosestForm form) {
        return super.doPost(form, webAdapter.filterByClosest(form.getLatitude(),
                                                             form.getLongitude(),
                                                             form.getHowMany()));
    }
}