package introsde.app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import introsde.resources.*;

public class HealthAppStandalone
{
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
    	String protocol = "http://";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostname);
        if (hostname.equals("127.0.0.1") || hostname.equals("127.0.1.1"))
        {
        	hostname = "localhost";
        }
        String port = "9004";
        //String BASE_URL = "/sdelab/";

        // We need this so the App will run on Heroku properly where we got the assigned port
        // in an environment value named PORT
        if(String.valueOf(System.getenv("PORT")) != "null") {
        	port = String.valueOf(System.getenv("PORT"));
        }

        URI baseUrl = new URI(protocol + hostname + ":" + port + "/");

        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
    }
    
    public static ResourceConfig createApp() {
    	System.out.println("Starting Process Service REST services...");
        return new HealthAppConfig();
    }
}
