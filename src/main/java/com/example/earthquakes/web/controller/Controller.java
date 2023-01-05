package com.example.earthquakes.web.controller;

import com.example.earthquakes.web.formdata.AbstractForm;

public interface Controller {
    String doPost(AbstractForm form);
}
