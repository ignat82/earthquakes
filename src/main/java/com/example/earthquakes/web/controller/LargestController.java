package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.LargestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class LargestController extends AbstractController {
    private final String PATH = LARGEST_PATH;

    public LargestController(WebAdapter webAdapter, EarthQuakeClient earthQuakeClient) {
        super(webAdapter, earthQuakeClient, DEPTH_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(LargestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(LargestForm form) {
        return super.doPost(form, webAdapter.filterByLargest(form.getHowMany()));
    }
}
