package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.filter.DistanceAdapter;
import com.example.earthquakes.web.formdata.DistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.DISTANCE_PATH;
import static com.example.earthquakes.entities.Constants.DISTANCE_TEMPLATE;

@Controller
public class DistanceController extends AbstractFilterController {
    private final String PATH = DISTANCE_PATH;

    public DistanceController(EarthQuakeClient earthQuakeClient,
                              DistanceAdapter adapter) {
        super(earthQuakeClient, adapter, DISTANCE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(DistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(DistanceForm form) {
        return super.doPost(form);
    }
}
