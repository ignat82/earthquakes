package com.example.earthquakes.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SampleController {
    @Value("${spring.application.name}")
    String appName;

//    @GetMapping("/")
//    public String samplePage(Model model) {
//
//        model.addAttribute("appName", appName);
//        log.info("appName set to {}", model.getAttribute("appName"));
//        return "sample";
//    }
    @GetMapping("/")
    public String samplePage(SampleModel sampleModel) {

        sampleModel.setAppName(appName);
        log.info("appName set to {}", sampleModel.getAppName());
        return "sample";
    }
}
