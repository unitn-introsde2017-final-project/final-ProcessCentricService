package introsde.resources;
import java.net.URI;

/*import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.tools.JavaFileObject;*/
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

@Path("/healthapp") // indicates the path under which this resource will be available
public class HealthApp {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHelloHtml() {
        return "{\"Application name\": \"Health App\", \"Copyright\": \"Sandor Tibor Nagy\", \"Matricola\":\"188895\", \"Project\":\"Introduction to Service Design and Engineering\", \"Documentation\":\"https://github.com/unitn-introsde2017-final-project\"}";
    }
    
    // When client wants HTML
    @GET
    @Path("/getPersonProfile") // you can pass path params to a service
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonProfile(@QueryParam("userId") int userId) {
    	System.out.println("--> getPersonProfile called with userId = " + userId);
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBusinessURI());

        String user_data = service.path("getPersonProfile").queryParam("userId", userId).request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
        System.out.println(user_data);
        
		return user_data;
    }
    
    @PUT
    @Path("/addUpdateStepCount") // you can pass path params to a service
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public String addUpdateStepCount(String payload) {
    	System.out.println("--> /addUpdateStepCount called with payload:");
    	System.out.println(payload);
    	
    	// Get JSON from the payload
    	//JsonReader jsonReader = Json.createParser(payload);
    	//JsonObject json_data = jsonReader.readObject();
    	JSONObject json_data = new JSONObject(payload);
    	
    	// Pass the data to the Storage Layer
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getStorageURI());
        
        String response_text = service.path("addUpdateStepCount").request().accept(MediaType.APPLICATION_JSON).put(Entity.json(payload.toString())).readEntity(String.class);
        //String response_text = service.path("addUpdateStepCount").request().accept(MediaType.APPLICATION_JSON).put(payload).readEntity(String.class);
        
		return response_text;
    }
    
    @POST
    @Path("/UserProfile") // you can pass path params to a service
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String addUpdateProfile(@QueryParam("userId") int userId, String payload) {
    	System.out.println("--> /addUpdateProfile called with userId = "+userId+" and payload:");
    	System.out.println(payload);
    	
    	// Get JSON from the payload
    	JSONObject json_data = new JSONObject(payload);
    	
    	// Pass the data to the Storage Layer
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getStorageURI());
        
        String response_text = service.path("UserProfile").queryParam("userId", userId).request().accept(MediaType.APPLICATION_JSON).post(Entity.json(json_data.toString())).readEntity(String.class);
        
		return response_text;
	}
    
    private static URI getBusinessURI() {
        //return UriBuilder.fromUri("http://localhost:9005/business").build();
		return UriBuilder.fromUri("http://final-business-service.herokuapp.com/business").build();
    }
    
    private static URI getStorageURI() {
        //return UriBuilder.fromUri("http://localhost:9001/StorageService").build();
		return UriBuilder.fromUri("http://final-storage-service.herokuapp.com/StorageService").build();
    }
}