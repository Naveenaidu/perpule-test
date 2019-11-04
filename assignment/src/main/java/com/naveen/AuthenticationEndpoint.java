package com.naveen;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("authentication")
public class AuthenticationEndpoint {
	
	private String admin_username = "admin";
	private String admin_password = "password";
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticateUser(Credentials c) {
		String username = c.getUsername();
		String password = c.getPassword();
		
		if (isUserAllowed(username, password)) {
			String token = issueToken(username, password);
			return Response.ok(token).build();
		}
		else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
	}
	
	private String issueToken(String username, String password) {
		String token = JwTokenHelper.getInstance().generatePrivateKey(username, password);
		return token;
	}

	// Check if the user is allowed
	private boolean isUserAllowed(String username, String password) {
		boolean isAllowed = false;
		if(username.equals(admin_username) && password.equals(admin_password)) {
			isAllowed = true;
		}
		
		return isAllowed;
	}
}
