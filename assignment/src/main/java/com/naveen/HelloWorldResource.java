package com.naveen;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
//The Java class will be hosted at the URI path "/helloworld"
@Path("abc")

public class HelloWorldResource {
	 @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getIt() {
	    	String runtimeVersion = System.getProperty("java.runtime.version");
	    	System.out.println(runtimeVersion);
	        return "Got ittttt!";
	    }
}
