package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.multi.MagPhraseMaxDistDepthAdapter;
import com.example.earthquakes.web.formdata.MagPhraseMaxDistDepthForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MAG_PHRASE_MAX_DIST_PATH;
import static com.example.earthquakes.entities.Constants.MAG_PHRASE_MAX_DIST_TEMPLATE;

@Controller
public class MagPhraseMaxDistDepthController extends AbstractController {
    private final String PATH = MAG_PHRASE_MAX_DIST_PATH;

    public MagPhraseMaxDistDepthController(EarthQuakeClient earthQuakeClient,
                                           MagPhraseMaxDistDepthAdapter adapter) {
        super(earthQuakeClient, adapter, MAG_PHRASE_MAX_DIST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MagPhraseMaxDistDepthForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MagPhraseMaxDistDepthForm form) {
        return super.doPost(form);
    }

}
