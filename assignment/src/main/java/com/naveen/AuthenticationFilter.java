package com.naveen;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final String TOKEN = "token";
	private JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();
	
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        //String authorizationHeader =
            //requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        String token = requestContext.getHeaderString(TOKEN);
        System.out.println(token);
        
        // Check if the HTTP Authorization header is present and formatted correctly
        if (token == null || token.isEmpty()) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        //String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
        	System.out.println(token);
            validateToken(token);
            System.out.println("TOKEN VALIDATED");

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
    	try {
    		jwTokenHelper.claimKey(token);  
       } catch(Exception e) {
           if (e instanceof ExpiredJwtException) {
               throw new WebApplicationException(token + " is expired");
           } else if (e instanceof MalformedJwtException) {
               throw new WebApplicationException(token + " is not correct"); 
       }
    }

    }	
}
