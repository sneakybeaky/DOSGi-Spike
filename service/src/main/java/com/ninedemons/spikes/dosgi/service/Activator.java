package com.ninedemons.spikes.dosgi.service;

import com.ninedemons.spikes.dosgi.shared.PingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {
    private ServiceRegistration reg;

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    public void start(BundleContext bc) throws Exception {
        Dictionary<String, Object> props = new Hashtable<String, Object>();

        props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.ws");
        props.put("org.apache.cxf.ws.address", "/display"); // old obsolete value

        reg = bc.registerService(PingService.class.getName(),
                new SimplePingService(), props);
    }


    public void stop(BundleContext bc) throws Exception {
        LOG.info("Unregistering ping service");

        reg.unregister();
    }

}
