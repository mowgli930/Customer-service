package se.lemv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.lemv.model.Customer;
import se.lemv.repository.CustomerRepository;

@Component
public class CustomerService {

	public final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer create(Customer customer) {
		return customerRepository.create(customer);
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
}
