package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.MagnitudeAdapter;
import com.example.earthquakes.web.formdata.MagnitudeForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.*;

@Controller
public class MagnitudeController extends AbstractController {
    private final String PATH = MAGNITUDE_PATH;

    public MagnitudeController(EarthQuakeClient earthQuakeClient) {
        super(earthQuakeClient, MAGNITUDE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MagnitudeForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MagnitudeForm form) {
        MagnitudeAdapter adapter = new MagnitudeAdapter(earthQuakeClient, form);
        return super.doPost(form, adapter.filterBy(form));
    }
}
