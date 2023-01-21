package com.example.earthquakes.web.controller.filter;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.filter.DepthAdapter;
import com.example.earthquakes.web.formdata.DepthForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.DEPTH_PATH;
import static com.example.earthquakes.entities.Constants.DEPTH_TEMPLATE;

@Controller
public class DepthController extends AbstractFilterController {
    private final String PATH = DEPTH_PATH;

    public DepthController(EarthQuakeClient earthQuakeClient,
                           DepthAdapter adapter) {
        super(earthQuakeClient, adapter, DEPTH_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(DepthForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(DepthForm form) {
        return super.doPost(form);
    }
}
