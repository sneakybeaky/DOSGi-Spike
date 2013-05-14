package com.ninedemons.spikes.dosgi.service;

import com.ninedemons.spikes.dosgi.shared.PingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {
    private ServiceRegistration reg;

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    public void start(BundleContext bc) throws Exception {
        Dictionary<String, Object> props = new Hashtable<String, Object>();

        String host = getHostName();
        int port = getPort();

        String address = getAddress(host,port);
        LOG.info("Registering ws address {}",address);

        props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.ws");
        props.put("org.apache.cxf.ws.address", address); // old obsolete value
        props.put("endpoint.id", address);

        reg = bc.registerService(PingService.class.getName(),
                new SimplePingService(), props);
    }

    private static String getAddress(String host, int port) throws Exception {
        return "http://" + host + ":" + port + "/display";
    }

    private static String getHostName() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }

    private static int getPort() throws IOException {
        return new ServerSocket(0).getLocalPort();
    }

    public void stop(BundleContext bc) throws Exception {
        LOG.info("Unregistering ping service");

        reg.unregister();
    }

}
