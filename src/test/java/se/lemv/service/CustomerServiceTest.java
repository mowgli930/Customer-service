package se.lemv.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import se.lemv.model.Customer;
import se.lemv.model.CustomerParser;
import se.lemv.repository.inMemoryRepository.InMemoryRepository;

public class CustomerServiceTest {
	
	private static CustomerService service = new CustomerService(new InMemoryRepository());
	private static Customer c1 = new Customer(1001L).setFirstName("Ale").setLastName("Jandro").setCustomerNumber(6901L);	
	private static Customer c2 = new Customer(1002L).setFirstName("Are").setLastName("kkusandaa").setCustomerNumber(6902L);
	private static Customer c3 = new Customer(1003L).setFirstName("Guido").setLastName("di Maffiatelli").setCustomerNumber(6903L);
	
	@BeforeClass
	public static void setUp() {
		service.save(c1);
		service.save(c2);
		service.save(c3);
	}
	
	@Test
	public void canGetCustomers() {
		List<Customer> customers = service.getCustomers(1001, 3, "asc");
		assertEquals(c1, customers.get(0));
		assertEquals(c2, customers.get(1));
		assertEquals(c3, customers.get(2));
	}
	
	@Test
	public void canGet2Customers() {
		int size = 2;
		List<Customer> customers = service.getCustomers(1001, size, "asc");
		assertEquals(customers.size(), size);
	}
	
	@Test
	public void canGetDescOrder() {
		List<Customer> customers = service.getCustomers(1001, 3, "desc");
		assertEquals(c3, customers.get(0));
		assertEquals(c2, customers.get(1));
		assertEquals(c1, customers.get(2));
	}
}
