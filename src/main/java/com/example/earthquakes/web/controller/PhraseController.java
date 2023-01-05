package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.PhraseForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.PHRASE_PATH;
import static com.example.earthquakes.entities.Constants.PHRASE_TEMPLATE;

@Controller
public class PhraseController extends AbstractController {
    private final String PATH = PHRASE_PATH;

    public PhraseController(WebAdapter webAdapter) {
        super(webAdapter, PHRASE_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(PhraseForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(PhraseForm form) {
        return super.doPost(form, webAdapter.filterByPhrase(form.getPhrase(),
                                                            form.getPosition()));
    }
}
