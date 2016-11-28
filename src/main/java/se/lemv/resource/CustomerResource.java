package se.lemv.resource;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;
import se.lemv.repository.inMemoryRepository.InMemoryRepository;

@Path("customer")
public class CustomerResource {
	
	private static CustomerRepository customerRepository = new InMemoryRepository();
	private static final AtomicLong customerIds = new AtomicLong(1000);

	@GET
	@Path("{id}")
	public Response getCustomer(String id) {
		return Response.ok(customerRepository.get(Long.parseLong(id))).build();
	}
	
	@POST
	public Response addCustomer(String content) {
		String[] split = content.split(";");
		Customer customer = new Customer(customerIds.incrementAndGet())
				.setCustomerNumber(Long.parseLong(split[0]))
				.setFirstName(split[1]).setLastName(split[2]);
		customer = customerRepository.create(customer);
		return Response.ok(customer.toString()).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateCustomer(String content) {
		String[] split = content.split(";");
		Customer customer = new Customer(customerIds.incrementAndGet())
				.setCustomerNumber(Long.parseLong(split[0]))
				.setFirstName(split[1]).setLastName(split[2]);
		customerRepository.update(customer.getId(), customer);
		return Response.ok(String.format("%d\n%d\n%s %s", customer.getId(), customer.getCustomerNumber(), customer.getFirstName(), customer.getLastName())).build();
	}
}
