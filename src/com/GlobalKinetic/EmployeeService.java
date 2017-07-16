package com.GlobalKinetic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/employees")

public class EmployeeService{
	
	Employee emp = new Employee();

    
	@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Employees getAllEmployees() 
	{
	    Employees list = new Employees();
	    list.setEmployeeList(new ArrayList<Employee>());
	     
	    list.getEmployeeList().add(new Employee(1, "Francois Pienaar"));
	    list.getEmployeeList().add(new Employee(2, "Tom DuToit"));
	    list.getEmployeeList().add(new Employee(3, "Karol Mathew"));
	     
	    return list;
	}	
	
	
	
@PermitAll	
@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_XML)
public Response updateEmployeeById(@PathParam("id") Integer id) 
{
    if(id  < 0){
        return Response.noContent().build();
    }
     
    emp.setId(id);
    emp.setName("Francois Pienaar");
     
    GenericEntity<Employee> entity = new GenericEntity<Employee>(emp, Employee.class);
    return Response.ok().entity(entity).build();
}


@PermitAll
@POST
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public Response addEmployee( Employee e ) throws URISyntaxException 
{
    if(e == null){
        return Response.status(400).entity("Please add employee details !!").build();
    }
     
    if(e.getName() == null) {
        return Response.status(400).entity("Please provide the employee name !!").build();
    }
     
    return Response.created(new URI("/rest/employees/"+e.getId())).build();
}


@PermitAll
@PUT
@Path("/{id}")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public Response updateEmployeeById(@PathParam("id") Integer id, Employee e) 
{
    Employee updatedEmployee = new Employee();
     
    if(e.getName() == null) {
        return Response.status(400).entity("Please provide the employee name !!").build();
    }
     
    updatedEmployee.setId(id);
    updatedEmployee.setName(e.getName());
     
    return Response.ok().entity(updatedEmployee).build();
}


@RolesAllowed("ADMIN")
@DELETE
@Path("/{id}")
public Response deleteEmployeeById(@PathParam("id") Integer id) 
{       
    return Response.status(202).entity("Employee deleted successfully !!").build();
}



}