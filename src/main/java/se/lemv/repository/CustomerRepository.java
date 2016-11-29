package se.lemv.repository;

import java.util.List;

import se.lemv.model.Customer;

public interface CustomerRepository {
	
	public Customer save(Customer customer);
	
	public Customer get(Long id);
	
	public Long update(Long id, Customer newCustomer);
	
	public Customer remove(Long id);
	
	public List<Customer> getCustomers(int page, int size, String sort);
	
}
