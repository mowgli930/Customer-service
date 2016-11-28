package se.lemv.model;

import org.springframework.core.style.ToStringCreator;

import jersey.repackaged.com.google.common.base.MoreObjects.ToStringHelper;

public final class Customer extends AbstractEntity {
	
	private String firstName;
	private String lastName;
	private Long customerNumber;
	
	public Customer(Long id) {
		super(id);
	}

	public String getFirstName() {
		return firstName;
	}

	public Customer setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Customer setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public Customer setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("id=%d\nCustomer Number=%d\nName=%s%s", getId(), getCustomerNumber(), getFirstName(), getLastName());
	}
	
}
