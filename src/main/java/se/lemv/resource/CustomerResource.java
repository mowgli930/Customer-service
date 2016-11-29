package se.lemv.resource;

import static se.lemv.model.CustomerParser.asString;
import static se.lemv.model.CustomerParser.asXml;
import static se.lemv.model.CustomerParser.fromString;

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
import se.lemv.service.CustomerService;

@Component
@Path("customer")
public class CustomerResource {
	
	@Autowired
	private CustomerService service;

	@POST
	public Response addCustomer(String content) {
		Customer customer = fromString(content);
		customer = service.save(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomerAsPlain(@PathParam("id") Long id) {
		return Response.ok(asString(service.get(id))).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomerAsXml(@PathParam("id") Long id) {
		return Response.ok(asXml(service.get(id))).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {
		Customer customer = fromString(content);
		service.update(id, customer);
		System.out.println(customer.getId());
		customer = service.get(id);
		System.out.println(customer.getId());
		return Response.ok(asString(customer)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		service.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
