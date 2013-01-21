package servis.worker;

//import servis.client.ClientData;

public class WorkerData {
	
	private int id_worker;
	private String name;
	private String surname;
	private String address;
	private int phone;
	
	
	/*	﻿CREATE TABLE worker
	(
	    	id_worker            	serial,
	    	name            		varchar(32)   	not null,
	   		surname         		varchar(32)   	not null,
	    	address            		varchar(100)  	not null,
	    	phone	 	    		int           	not null,
	    	comments            	varchar(120),
		CONSTRAINT		worker_id_worker_pk PRIMARY KEY(id_worker)
	);

	 */
	public WorkerData(String name, String surname, String address, int phone) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
	}
	
	public WorkerData(String surname) {
		this.surname = surname;
	}
	
	public long getIdWorker() {
		return id_worker;
	}
	public void setIdWorker(int id_worker) {
		this.id_worker = id_worker;
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/// PO CO ??? 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_worker ^ (id_worker >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkerData other = (WorkerData) obj;
		if (id_worker != other.id_worker)
			return false;
		return true;
	}
}