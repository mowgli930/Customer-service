package se.lemv.repository.inMemoryRepository;

import java.util.HashMap;
import java.util.Map;

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;

public class InMemoryRepository implements CustomerRepository {

	private Map<Long, Customer> customers = new HashMap<Long, Customer>();
	
	@Override
	public Customer create(Customer customer) {
		customers.put(customer.getId(), customer);
		return customer;
	}

	@Override
	public Customer get(Long id) {
		return customers.get(id);
	}

	@Override
	public Long update(Long id, Customer newCustomer) {
		customers.replace(id, newCustomer);
		return id;
	}

	@Override
	public Customer delete(Long id) {
		Customer deletedCustomer = customers.get(id);
		customers.remove(id);
		return deletedCustomer;
	}

}
