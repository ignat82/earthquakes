package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.filter.MaxDistanceAdapter;
import com.example.earthquakes.web.formdata.MaxDistanceForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MAX_DISTANCE_PATH;
import static com.example.earthquakes.entities.Constants.MAX_DISTANCE_TEMPLATE;

@Controller
public class MaxDistanceController extends AbstractFilterController {
    private final String PATH = MAX_DISTANCE_PATH;

    public MaxDistanceController(EarthQuakeClient earthQuakeClient,
                                 MaxDistanceAdapter adapter) {
        super(earthQuakeClient, adapter, MAX_DISTANCE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MaxDistanceForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MaxDistanceForm form) {
        return super.doPost(form);
    }
}
