package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.ClosestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.CLOSEST_TEMPLATE;

@Controller
public class ClosestController extends AbstractController {
    public ClosestController(WebAdapter webAdapter) {
        super(webAdapter, CLOSEST_TEMPLATE);
    }

    @GetMapping("/earthquake/closest")
    public String doGet(ClosestForm form) {
        return super.doGet(form);
    }

    @PostMapping("/earthquake/closest")
    public String doPost(ClosestForm form) {
        return super.doPost(form, webAdapter.filterByClosest(form.getLatitude(),
                                                             form.getLongitude(),
                                                             form.getHowMany()));
    }
}
