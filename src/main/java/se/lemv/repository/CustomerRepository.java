package se.lemv.repository;

import se.lemv.model.Customer;

public interface CustomerRepository {
	
	public Customer create(Customer customer);
	
	public Customer get(Long id);
	
	public Long update(Long id, Customer newCustomer);
	
	public Customer remove(Long id);
	
}
