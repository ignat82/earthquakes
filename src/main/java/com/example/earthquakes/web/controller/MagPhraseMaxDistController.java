package com.example.earthquakes.web.controller;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.multi.MagPhraseMaxDistAdapter;
import com.example.earthquakes.web.formdata.MagPhraseMaxDistForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.MAG_PHRASE_MAX_DIST_PATH;
import static com.example.earthquakes.entities.Constants.MAG_PHRASE_MAX_DIST_TEMPLATE;

@Controller
public class MagPhraseMaxDistController extends AbstractController {
    private final String PATH = MAG_PHRASE_MAX_DIST_PATH;

    public MagPhraseMaxDistController(EarthQuakeClient earthQuakeClient,
                                      MagPhraseMaxDistAdapter adapter) {
        super(earthQuakeClient, adapter, MAG_PHRASE_MAX_DIST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(MagPhraseMaxDistForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(MagPhraseMaxDistForm form) {
        return super.doPost(form);
    }

}
