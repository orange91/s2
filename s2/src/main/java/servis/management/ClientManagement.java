package servis.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servis.client.ClientData;





public class ClientManagement implements ManagementInterface<ClientData> {

private Connection connection;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable ="CREATE TABLE client ("+
	    	"id            	serial,"+
	    	"name            		varchar(32)   	not null,"+
	   		"surname         		varchar(32)   	not null,"+
	    	"address            		varchar(100)  	not null,"+
	    	"phone	     		varchar(10)          	not null,"+	 
	    	"comments            	varchar(120),"+
	    	"CONSTRAINT		client_id_pk PRIMARY KEY(id)"+
	");";
	/*	﻿CREATE TABLE client
	(
	    	id            	serial,
	    	name            		varchar(32)   	not null,
	   		surname         		varchar(32)   	not null,
	    	address            		varchar(100)  	not null,
	    	phone	     			           	not null,
	    	comments            	varchar(120),
		CONSTRAINT		client_id_pk PRIMARY KEY(id)
	);

	 */
 
	Statement statement;
	PreparedStatement addClientDataStatement;
	PreparedStatement deleteClientDataStatement;
	PreparedStatement getAllClientDataStatement;
	PreparedStatement getClientDataByIdStatement;
	
	public ClientManagement() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("client".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if(!tableExists)
				statement.executeUpdate(createTable);

			addClientDataStatement = connection
					.prepareStatement(
							"INSERT INTO client (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteClientDataStatement = connection
					.prepareStatement("delete from client where surname=?");
			getAllClientDataStatement = connection
					.prepareStatement("select * from client");
			getClientDataByIdStatement = connection
					.prepareStatement("select * from client where id=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return connection;
	}
	
@Override 	
public ClientData get(long id) {
	
	ClientData result = null;
		
	try {
		getClientDataByIdStatement.setLong(1, id);
		ResultSet rs = getClientDataByIdStatement.executeQuery();
		while(rs.next()){
			result = new ClientData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("phone"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	@Override // fytj
	public List<ClientData> getAll() {
		List<ClientData> result= new ArrayList<ClientData>();
	
	try {
		ResultSet rs= getAllClientDataStatement.executeQuery();
		while(rs.next())
			result.add(new ClientData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("phone")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
@Override //fytj
	public boolean save(ClientData obj) {
		try {
			addClientDataStatement.setString(1, obj.getName());
			addClientDataStatement.setString(2, obj.getSurname());
			addClientDataStatement.setString(3, obj.getAddress());
			addClientDataStatement.setString(4, obj.getPhone());
			return addClientDataStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
@Override //fytj
	public boolean delete(ClientData obj) {
		try {
			deleteClientDataStatement.setString(1, obj.getAddress());
			deleteClientDataStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
//abstract
}
