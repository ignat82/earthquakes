package com.example.earthquakes.filter;

import com.example.earthquakes.entities.QuakeEntry;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
public class DepthFilter implements Filter{
    private final double minDepth;
    private final double maxDepth;

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() < maxDepth && qe.getDepth() > minDepth);
    }
}
