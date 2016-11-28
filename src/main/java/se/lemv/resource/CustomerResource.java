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

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;
import se.lemv.repository.inMemoryRepository.InMemoryRepository;

@Path("customer")
public class CustomerResource {
	
	private static CustomerRepository customerRepository = new InMemoryRepository();
	private static final AtomicLong customerIds = new AtomicLong(1000);

	@POST
	public Response addCustomer(String content) {
		Long id = customerIds.incrementAndGet();
		String[] split = content.split(";");
		Customer customer = new Customer(id)
				.setCustomerNumber(Long.parseLong(split[0]))
				.setFirstName(split[1]).setLastName(split[2]);
		
		customer = customerRepository.create(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + id).build();
	}
	
	@GET
	@Path("{id}")
	public Response getCustomer(Long id) {
		System.out.println(id);
		System.out.println(customerRepository.get(id).getFirstName());
		return Response.ok(customerRepository.get(id).getFirstName()).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {
		String[] split = content.split(";");
		Customer customer = new Customer(id)
				.setCustomerNumber(id)
				.setFirstName(split[1]).setLastName(split[2]);
		customerRepository.update(customer.getId(), customer);
		customer = customerRepository.get(id);
		return Response.ok(customer.toString()).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(Long id) {
		customerRepository.delete(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
