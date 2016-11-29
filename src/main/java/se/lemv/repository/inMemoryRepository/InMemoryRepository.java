package se.lemv.repository.inMemoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;

@Component
public class InMemoryRepository implements CustomerRepository {

	private Map<Long, Customer> customers = new HashMap<Long, Customer>();
	
	@Override
	public Customer save(Customer customer) {
		customers.put(customer.getId(), customer);
		return customer;
	}

	@Override
	public Customer get(Long id) {
		return customers.get(id);
	}

	@Override
	public Long update(Long id, Customer newCustomer) {
		Customer oldCustomer = customers.get(id);
		oldCustomer.setCustomerNumber(newCustomer.getCustomerNumber());
		oldCustomer.setFirstName(newCustomer.getFirstName());
		oldCustomer.setLastName(newCustomer.getLastName());
		return id;
	}

	@Override
	public Customer remove(Long id) {
		Customer deletedCustomer = customers.get(id);
		customers.remove(id);
		return deletedCustomer;
	}

	@Override //TODO this method should not be like this
	public List<Customer> getCustomers(int page, int size, String sort) {
		List<Customer> list = new ArrayList<Customer>();
		boolean asc;

		if(sort.equals("asc"))
			asc = true;
		else
			asc = false;

		
		for(Customer c: customers.values()) {
				if(c.getId() >= page && c.getId() < page+size)
					list.add(c);
		}
		if(!asc) {
			List<Customer> reversed = new ArrayList<Customer>();
			for(int i = 0; i < list.size(); i++) {
				reversed.add(list.get(size-1-i));
			}
			list = reversed;
		}
		return list;
	}

}
