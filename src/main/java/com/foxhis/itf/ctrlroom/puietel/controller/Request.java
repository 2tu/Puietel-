package com.foxhis.itf.ctrlroom.puietel.controller;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	private String query;
 
	public String getQuery() {
		return query;
	}
 
	public void setQuery(String query) {
		this.query = query;
	}
}
