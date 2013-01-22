package servis.client;

public class ClientData {

	private long id;
	private String name;
	private String surname;
	private String address;
	private String phone;
	
	/*	﻿CREATE TABLE client
	(
	    	id            	serial,
	    	name            		varchar(32)   	not null,
	   		surname         		varchar(32)   	not null,
	    	address            		varchar(100)  	not null,
	    	phone	     		int           	not null,
	    	comments            	varchar(120),
		CONSTRAINT		client_id_pk PRIMARY KEY(id)
	);

	 */
	public ClientData(String name, String surname, String address, String phone) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
	}
	
	public ClientData(String surname) {
		this.surname = surname;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
