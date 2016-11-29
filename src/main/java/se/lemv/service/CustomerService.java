package se.lemv.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;

@Component
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final AtomicLong customerIds = new AtomicLong(1000);

	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer save(Customer customer) {
		Long id = customerIds.incrementAndGet();
		Customer c = new Customer(id).setCustomerNumber(customer.getCustomerNumber())
				.setFirstName(customer.getFirstName())
				.setLastName(customer.getLastName());
		return customerRepository.save(c);
	}

	public Customer get(Long id) {
		return customerRepository.get(id);
	}

	public Long update(Long id, Customer customer) {
		return customerRepository.update(id, customer);
	}

	public Customer remove(Long id) {
		return customerRepository.remove(id);
	}
	
	public List<Customer> getCustomers(int page, int size, String sort) {
		return customerRepository.getCustomers(page, size, sort);
	}
	
}
