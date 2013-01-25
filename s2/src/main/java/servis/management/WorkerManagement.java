package servis.management;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import servis.worker.WorkerData;


public class WorkerManagement implements ManagementInterface<WorkerData>{
Connection connection;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable ="CREATE TABLE worker ("+
	    			"id          	serial,"+
	    			"name            		varchar(32)   	not null,"+
	   				"surname         		varchar(32)   	not null,"+
					"address            		varchar(100)  	not null,"+
	    			"phone	 	    		varchar(10)          	not null,"+
	    			"comments            	varchar(120),"+
					"CONSTRAINT				worker_id_pk PRIMARY KEY(id)"+
					");";
 
	Statement statement;
	PreparedStatement addWorkerDataStatement;
	PreparedStatement deleteWorkerDataStatement;
	PreparedStatement getAllWorkerDataStatement;
	PreparedStatement getWorkerDataByIdStatement;
	
	/*	﻿CREATE TABLE worker
	(
	    	id          	serial,
	    	name            		varchar(32)   	not null,
	   		surname         		varchar(32)   	not null,
	    	address            		varchar(100)  	not null,
	    	phone	 	    		int           	not null,
	    	comments            	varchar(120),
		CONSTRAINT		worker_id_worker_pk PRIMARY KEY(id_worker)
	);

	 */
	public WorkerManagement() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("worker".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if(!tableExists)
				statement.executeUpdate(createTable);

			addWorkerDataStatement = connection
					.prepareStatement(
							"INSERT INTO worker (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteWorkerDataStatement = connection
					.prepareStatement("delete from worker where id=?");
			getAllWorkerDataStatement = connection
					.prepareStatement("select * from worker");
			getWorkerDataByIdStatement = connection
					.prepareStatement("select * from worker where id=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	
	@Override
	public WorkerData get(long id) {
	
	WorkerData result = null;
		
	try {
		getWorkerDataByIdStatement.setLong(1, id);
		ResultSet rs = getWorkerDataByIdStatement.executeQuery();
		while(rs.next()){
			result = new WorkerData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("phone"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	@Override
	public List<WorkerData> getAll() {
		List<WorkerData> result= new ArrayList<WorkerData>();
	
	try {
		ResultSet rs= getAllWorkerDataStatement.executeQuery();
		while(rs.next())
			result.add(new WorkerData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("phone")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
@Override 
	public boolean save(WorkerData obj) {
		try {
			addWorkerDataStatement.setString(1, obj.getName());
			addWorkerDataStatement.setString(2, obj.getSurname());
			addWorkerDataStatement.setString(3, obj.getAddress());
			addWorkerDataStatement.setString(4, obj.getPhone());
			return addWorkerDataStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	@Override 
	public boolean delete(WorkerData obj) {
		try {
			deleteWorkerDataStatement.setLong(1, obj.getId());
			deleteWorkerDataStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
//abstract
}
