package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.filter.MinMagAdapter;
import com.example.earthquakes.web.formdata.MinMagForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MIN_MAG_PATH;
import static com.example.earthquakes.entities.Constants.MIN_MAG_TEMPLATE;

@Controller
public class MinMagController extends AbstractFilterController {
    private final String PATH = MIN_MAG_PATH;

    public MinMagController(EarthQuakeClient earthQuakeClient,
                            MinMagAdapter adapter) {
        super(earthQuakeClient, adapter, MIN_MAG_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MinMagForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MinMagForm form) {
        return super.doPost(form);
    }
}
