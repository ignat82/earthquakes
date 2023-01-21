package com.example.earthquakes.web.controller.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.comparator.LargestAdapter;
import com.example.earthquakes.web.formdata.LargestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.LARGEST_PATH;
import static com.example.earthquakes.entities.Constants.LARGEST_TEMPLATE;

@Controller
public class LargestController extends AbstractComparatorController {
    private final String PATH = LARGEST_PATH;

    public LargestController(EarthQuakeClient earthQuakeClient,
                             LargestAdapter adapter) {
        super(earthQuakeClient, adapter, LARGEST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(LargestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(LargestForm form) {
        return super.doPost(form);
    }
}
