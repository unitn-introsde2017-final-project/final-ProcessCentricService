package introsde.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("sdelab")
public class HealthAppConfig extends ResourceConfig {
    public HealthAppConfig () {
        packages("introsde");
    }
}
