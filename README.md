1. Build this project

        mvn clean install

2. Download & start up a zookeeper service

3. In the Karaf console do

    ```bash
    karaf@root> features:chooseurl cxf-dosgi 1.4.0
    karaf@root> features:install cxf-dosgi-discovery-distributed
    karaf@root> features:addUrl mvn:com.ninedemons.spikes.dosgi/features/1.0.0-SNAPSHOT/xml/features
    karaf@root> features:install dosgi-service
    ```

4. In the karaf log you will see an endpoint has been registered for the service :

        Registering ws address http://192.168.0.26:58473/display

5. In the zookeeper command line do

    ```[zk: localhost:2181(CONNECTED) 11]  get /osgi/service_registry/com/ninedemons/spikes/dosgi/shared/PingService/192.168.0.26#58473##display```
You will see details of the service :

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <endpoint-descriptions xmlns="http://www.osgi.org/xmlns/rsa/v1.0.0">
      <endpoint-description>
        <property name="endpoint.service.id" value-type="Long" value="235" />
        <property name="service.imported.configs">
          <array>
            <value>org.apache.cxf.ws</value>
          </array>
        </property>
        <property name="service.intents">
          <array>
            <value>SOAP.1_1</value>
            <value>HTTP</value>
            <value>SOAP</value>
          </array>
        </property>
        <property name="objectClass">
          <array>
            <value>com.ninedemons.spikes.dosgi.shared.PingService</value>
          </array>
        </property>
        <property name="endpoint.framework.uuid" value="2acd8e7f-1764-4c9a-aa8e-946ad926b1b7" />
        <property name="service.imported" value="true" />
        <property name="endpoint.id" value="http://192.168.0.26:58473/display" />
        <property name="org.apache.cxf.ws.address" value="http://192.168.0.26:58473/display" />
        <property name="endpoint.package.version.com.ninedemons.spikes.dosgi.shared" value="1.0.0.SNAPSHOT" />
      </endpoint-description>
    </endpoint-descriptions>
    ```

6. You can also see the service is running by requesting the wsdl : http://192.168.0.26:58473/display?wsdl
7. In the Karaf console stop the bundle that conatins the service :
    ```bash
    karaf@root> la
    START LEVEL 100 , List Threshold: 0
    ID   State         Blueprint      Level  Name
    [ 116] [Active  ] [            ] [   80] Distributed OSGi Discovery Spike Services Bundle (1.0.0.SNAPSHOT)
    karaf@root> stop 116
    ```

8. At this point the service should no longer be available, but it is. You can still see the wsdl and the entry in zookeeper