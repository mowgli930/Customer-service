package se.lemv.model;

import java.util.Arrays;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PageRequestBean {
	
	@QueryParam("page")
	private int page;
	
	@QueryParam("size")
	@DefaultValue("10")
	private int size;
	
	@QueryParam("sort")
	@DefaultValue("asc")
	private String sort;
	
	public static PageRequestBean valueOf(String value) {
		String[] split = value.split("&?");
		for(String s: Arrays.asList(split)) {
			System.out.println(s);
		}
		return null;
	}
	
	public int getPage() {
		return page;
	}
	public int getSize() {
		return size;
	}
	public String getSort() {
		return sort;
	}
	
}
