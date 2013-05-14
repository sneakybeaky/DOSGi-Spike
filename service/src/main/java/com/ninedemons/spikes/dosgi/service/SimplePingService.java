package com.ninedemons.spikes.dosgi.service;

import com.ninedemons.spikes.dosgi.shared.PingService;

public class SimplePingService implements PingService {
    @Override
    public String ping(String suffix) {
        return "PONG " + suffix;
    }
}
