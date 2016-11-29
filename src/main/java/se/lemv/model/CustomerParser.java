package se.lemv.model;

import nu.xom.Document;
import nu.xom.Element;

public class CustomerParser {

	public static Customer fromString(String values) {
		String[] split = values.split(";");
		Customer customer = new Customer(0L)
				.setCustomerNumber(Long.parseLong(split[0]))
				.setFirstName(split[1]).setLastName(split[2]);
		return customer;
	}
	
	public static String asString(Customer customer) {
		String customerAsString = String.format("%d;%d;%s;%s",
				customer.getId(), customer.getCustomerNumber(),
				customer.getFirstName(), customer.getLastName());
		return customerAsString;
	}
	
	public static String asXml(Customer customer) {
		
		try {
			Element root = new Element("customer");
			root.appendChild(createElement("id", customer.getId().toString()));
			root.appendChild(createElement("firstName", customer.getFirstName()));
			root.appendChild(createElement("lastName", customer.getLastName()));
			root.appendChild(createElement("number", customer.getCustomerNumber().toString()));
			
			return new Document(root).toXML(); 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Element createElement(String name, String value) {
		Element element = new Element(name);
		element.appendChild(value);
		return element;
	}
}
