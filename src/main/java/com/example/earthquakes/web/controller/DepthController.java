package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.DepthAdapter;
import com.example.earthquakes.web.adapter.WebAdapter;
import com.example.earthquakes.web.formdata.DepthForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class DepthController extends AbstractController {
    private final String PATH = DEPTH_PATH;

    public DepthController(WebAdapter webAdapter, EarthQuakeClient earthQuakeClient) {
        super(webAdapter, earthQuakeClient, DEPTH_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(DepthForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(DepthForm form) {
//        return super.doPost(form, webAdapter.filterByDepth(form));
        DepthAdapter adapter = new DepthAdapter(earthQuakeClient, form);
        return super.doPost(form, adapter.filterBy(form));
    }
}
