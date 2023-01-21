package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import com.example.earthquakes.web.formdata.DepthForm;

public class DepthFilter extends AbstractFilter implements Filter {
    private final double minDepth;
    private final double maxDepth;

    public DepthFilter(DepthForm depthForm) {
        super(DepthFilter.class.getName());
        minDepth = Double.parseDouble(depthForm.getMinDepth());
        maxDepth = Double.parseDouble(depthForm.getMaxDepth());
    }

    public DepthFilter(String minDepth, String maxDepth) {
        super(DepthFilter.class.getName());
        this.minDepth = Double.parseDouble(minDepth);
        this.maxDepth = Double.parseDouble(maxDepth);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() <= maxDepth && qe.getDepth() >= minDepth);
    }
}
