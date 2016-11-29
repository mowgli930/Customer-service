package se.lemv.model;

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
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		else if(obj instanceof Customer) {
			Customer otherCustomer = (Customer) obj;
			return super.id.equals(otherCustomer.getId()) &&
					customerNumber.equals(otherCustomer.customerNumber) &&
					firstName.equals(otherCustomer.firstName) &&
					lastName.equals(otherCustomer.lastName);
		}
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result += 31 * super.id.hashCode();
		result += 31 * customerNumber.hashCode();
		result += 31 * firstName.hashCode();
		result += 31 * lastName.hashCode();
		return result;
	}
}
