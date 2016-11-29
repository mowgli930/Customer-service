package se.lemv.resource;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	private CustomerService customerService;
	private static final AtomicLong customerIds = new AtomicLong(1000);

	@POST
	public Response addCustomer(String content) {
		Long id = customerIds.incrementAndGet();
		String[] split = content.split(";");
		Customer customer = new Customer(id)
				.setCustomerNumber(Long.parseLong(split[0]))
				.setFirstName(split[1]).setLastName(split[2]);
		
		customer = customerService.create(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + id).build();
	}
	
	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Long id) {
		return Response.ok(customerService.get(id).toString()).build();
	}
	
//	@GET
//	@Path("{id}")
//	public Response getCustomers()
	
	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {
		String[] split = content.split(";");
		Customer customer = new Customer(id)
				.setCustomerNumber(id)
				.setFirstName(split[1]).setLastName(split[2]);
		customerService.update(customer.getId(), customer);
		customer = customerService.get(id);
		return Response.ok(customer.toString()).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		customerService.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
