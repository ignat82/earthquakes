package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.ClosestAdapter;
import com.example.earthquakes.web.formdata.ClosestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class ClosestController extends AbstractController {
    private final String PATH = CLOSEST_PATH;

    public ClosestController(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient, CLOSEST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(ClosestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(ClosestForm form) {
        ClosestAdapter adapter = new ClosestAdapter(earthQuakeClient);
        return super.doPost(form, adapter.filterByClosest(form.getLatitude(),
                                                          form.getLongitude(),
                                                          form.getHowMany()));
    }
}
