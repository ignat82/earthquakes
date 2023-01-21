package com.example.earthquakes.web.controller.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.comparator.ClosestAdapter;
import com.example.earthquakes.web.formdata.ClosestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.CLOSEST_PATH;
import static com.example.earthquakes.entities.Constants.CLOSEST_TEMPLATE;

@Controller
public class ClosestController extends AbstractComparatorController {
    private final String PATH = CLOSEST_PATH;

    public ClosestController(EarthQuakeClient earthQuakeClient,
                             ClosestAdapter adapter) {
        super(earthQuakeClient, adapter, CLOSEST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(ClosestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(ClosestForm form) {
        return super.doPost(form);
    }
}
