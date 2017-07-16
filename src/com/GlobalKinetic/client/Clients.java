package com.GlobalKinetic.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.GlobalKinetic.Employee;
import com.GlobalKinetic.Employees;


public class Clients 
{
	public static void main(String[] args) throws IOException 
	{
		httpGETCollection();
		httpGETEntity();
		httpPOSTMethod();
		httpPUTMethod();
		httpDELETEMethod();
	}
	
	private static void httpGETCollection() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
																    .nonPreemptive()
																    .credentials("user", "password")
																    .build();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/GlobalKinetic/rest").path("employees");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Employees employees = response.readEntity(Employees.class);
		List<Employee> listOfEmployees = employees.getEmployeeList();
			
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfEmployees.toArray(new Employee[listOfEmployees.size()]) ));
	}
	
	private static void httpGETEntity() 
	{
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
			    .nonPreemptive()
			   .credentials("user", "password")
			    .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/GlobalKinetic/rest").path("employees").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Employee employee = response.readEntity(Employee.class);
			
		System.out.println(response.getStatus());
		System.out.println(employee);
	}

	private static void httpPOSTMethod() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
			    .nonPreemptive()
			    .credentials("user", "password")
			   .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/GlobalKinetic/rest").path("employees");
		
		Employee emp = new Employee();
		emp.setId(4);
		emp.setName("Marika Bester");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_XML));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	private static void httpPUTMethod() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
			    .nonPreemptive()
			    .credentials("user", "password")
			    .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/GlobalKinetic/rest").path("employees").path("1");
		
		Employee emp = new Employee();
		emp.setId(2);
		emp.setName("Jeanette Merkel");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(emp, MediaType.APPLICATION_XML));
		
		Employee employee = response.readEntity(Employee.class);
			
		System.out.println(response.getStatus());
		System.out.println(employee);
	}
	
	private static void httpDELETEMethod() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
			    .nonPreemptive()
			    .credentials("user", "password")
			    .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/GlobalKinetic/rest").path("employees").path("3");
		
		Invocation.Builder invocationBuilder =	webTarget.request();
		Response response = invocationBuilder.delete();
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}
