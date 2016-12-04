package se.lemv.resource;

import static se.lemv.model.CustomerParser.asString;
import static se.lemv.model.CustomerParser.asXml;
import static se.lemv.model.CustomerParser.fromString;
import static se.lemv.model.CustomerParser.fromXml;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.lemv.model.Customer;
import se.lemv.model.PageRequestBean;
import se.lemv.service.CustomerService;

@Component
@Path("customer")
public class CustomerResource {
	
	@Autowired
	private CustomerService service;

	@POST
	@Path("text")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addCustomerAsPlain(String content) {
		Customer customer = fromString(content);
		customer = service.save(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@POST
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML) //Can be created with Id but will be given a new one
	public Response addCustomerAsXml(String content) {
		Customer customer = fromXml(content);
		customer = service.save(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@GET
	@Path("text/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomerAsPlain(@PathParam("id") Long id) {
		Customer customer = service.get(id);
		if(customer != null)
			return Response.ok(asString(customer)).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("text/all") //http://127.0.0.1:8080/text/all?page=1001&size=3&sort=asc
	public Response getAllCustomersAsPlain(@BeanParam PageRequestBean request) {
		System.out.printf("%s\n%s\n%s", request.getPage(), request.getSize(), request.getSort());
		List<Customer> customers = service.getCustomers(request.getPage(), request.getSize(), request.getSort());
		
		if(customers != null) {
			StringBuilder allCustomers = new StringBuilder();
			customers.forEach(c -> allCustomers.append(asString(c) + "\n"));
			return Response.ok(allCustomers.toString()).build();			
		}
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("xml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomerAsXml(@PathParam("id") Long id) {
		Customer customer = service.get(id);
		if(customer != null)
			return Response.ok(asXml(customer)).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("xml/all") //http://127.0.0.1:8080/text/all?page=1001&size=3&sort=asc
	public Response getAllCustomersAsXml(@BeanParam PageRequestBean request) {
		List<Customer> customers = service.getCustomers(request.getPage(), request.getSize(), request.getSort());
		
		if(customers != null) {
			StringBuilder allCustomers = new StringBuilder();
			customers.forEach(c -> allCustomers.append(asXml(c) + "\n"));
			return Response.ok(allCustomers.toString()).build();
		}
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {
		Customer customer = fromString(content);
		service.update(id, customer);
		customer = service.get(id);
		return Response.ok(asString(customer)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		service.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
