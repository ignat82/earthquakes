package com.example.earthquakes.web.controller.comparator;

import com.example.earthquakes.EarthQuakeClient;
import com.example.earthquakes.web.adapter.single.comparator.DeepestAdapter;
import com.example.earthquakes.web.formdata.DeepestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.DEEPEST_PATH;
import static com.example.earthquakes.entities.Constants.DEEPEST_TEMPLATE;

@Controller
public class DeepestController extends AbstractComparatorController {
    private final String PATH = DEEPEST_PATH;

    public DeepestController(EarthQuakeClient earthQuakeClient,
                             DeepestAdapter adapter) {
        super(earthQuakeClient, adapter, DEEPEST_TEMPLATE);
    }

    @GetMapping(PATH)
    public String doGet(DeepestForm form) {
        return super.doGet(form);
    }

    @PostMapping(PATH)
    public String doPost(DeepestForm form) {
        return super.doPost(form);
    }
}
