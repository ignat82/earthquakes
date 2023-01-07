package com.example.earthquakes.web.adapter.multi;

import com.example.earthquakes.EarthQuakeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractMultiFilterAdapter {
    protected final EarthQuakeClient earthQuakeClient;

}
