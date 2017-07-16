package com.GlobalKinetic;

import org.glassfish.jersey.server.ResourceConfig;
 

 
public class Register extends ResourceConfig 
{
    public Register() 
    {
        packages("com.GlobalKinetic");
        register(AuthenticationFilter.class);
    }
}