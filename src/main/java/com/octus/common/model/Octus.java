package com.octus.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "octus")
public class Octus {

	@Id
	private String id;

	private String test;

	public Octus() {
	}

	public Octus(String id, String test) {
		super();
		this.id = id;
		this.test = test;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}