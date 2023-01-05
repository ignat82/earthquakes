package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.WebAdapter;
import com.example.earthquakes.web.formdata.DepthForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.earthquakes.entities.Constants.DEPTH_TEMPLATE;

@Controller
public class DepthController extends AbstractController {
    public DepthController(WebAdapter webAdapter) {
        super(webAdapter, DEPTH_TEMPLATE);
    }

    @GetMapping("/earthquake/depth")
    public String doGet(DepthForm form) {
        return super.doGet(form);
    }

    @PostMapping("/earthquake/depth")
    public String doPost(DepthForm form) {
        initializeFormForPost(form, webAdapter.filterByDepth(form.getMinDepth(), form.getMaxDepth()));
        return DEPTH_TEMPLATE;
    }

}
