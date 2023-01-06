package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.MinMagForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class MinMagController extends AbstractController {
    private final String PATH = MIN_MAG_PATH;

    public MinMagController(WebAdapter webAdapter) {
        super(webAdapter, MIN_MAG_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MinMagForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MinMagForm form) {
        return super.doPost(form, webAdapter.filterByMinMagnitude(form.getMinMag()));
    }
}