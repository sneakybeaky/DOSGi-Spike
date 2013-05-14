package com.ninedemons.spikes.dosgi.client;

import com.ninedemons.spikes.dosgi.shared.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jon Barber
 */
public class PollingClient {

    private static final Logger LOG = LoggerFactory.getLogger(PollingClient.class);


    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> handle;


    private PingService pingService;

    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }

    public void startPolling() {
        LOG.info("Starting to poll");

        scheduler = Executors.newScheduledThreadPool(1);
        Runnable printer = new Runnable() {
            int counter;
            public void run() {
                counter++;

                LOG.info("Poll result is {}", pingService.ping(Integer.toString(counter)));
            }
        };
        handle = scheduler.scheduleAtFixedRate(printer, 5, 5, TimeUnit.SECONDS);

    }

    public void stopPolling()  {
        LOG.info("Stopping polling");
        handle.cancel(true);
    }

}
