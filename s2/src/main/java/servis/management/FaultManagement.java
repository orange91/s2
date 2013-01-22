package servis.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servis.fault.FaultData;



/*
 CREATE TABLE fault
(
id_fault        		serial,
description_fault       varchar(500)    not null,
date_solution_fault    	date,
what_do       			varchar(300),
extend            		varchar(200),
CONSTRAINT        fault_id_fault_pk PRIMARY KEY(id_fault)
);
 */



public class FaultManagement implements ManagementInterface<FaultData> {
Connection connection;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable = "CREATE TABLE fault ("+
						 "id		       	 serial,"+
						 "description_fault  varchar(500)    not null,"+
						 "date_solution_fault    	date,"+
						 "what_do  			 varchar(300),"+
						 "extend      		varchar(200),"+
						 "CONSTRAINT        fault_id_fault_pk PRIMARY KEY(id_fault)"+
						 ");";

	
	
	Statement statement;
	PreparedStatement addFaultStatement;
	PreparedStatement deleteFaultStatement;
	PreparedStatement getAllFaultStatement;
	PreparedStatement getFaultByIdStatement;
	
	public FaultManagement() {
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

			addFaultStatement = connection
					.prepareStatement(
							"INSERT INTO xxxxxxx (description_fault, date_solution_fault, what_do) " +
							"VALUES (?, ?, ?)");
			deleteFaultStatement = connection
					.prepareStatement("delete from fault where id=?");
			getAllFaultStatement = connection
					.prepareStatement("select * from fault");
			getFaultByIdStatement = connection
					.prepareStatement("select * from fault where id=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return connection;
	}
	/*
	 this.description_fault = description_fault;
		this.date_solution_fault =  date_solution_fault;
		this.what_do = what_do;
		
		
	 * (non-Javadoc)
	 * @see servis.management.ManagementInterface#get(long)
	 */
	
	// @Override fytj
	public FaultData get(long id) {
	
	FaultData result = null;
		
	try {
		getFaultByIdStatement.setLong(1, id);
		ResultSet rs = getFaultByIdStatement.executeQuery();
		while(rs.next()){
			result = new FaultData(rs.getString("description_fault"),rs.getString("date_solution_fault"),rs.getString("what_do"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	// @Override fytj
	public List<FaultData> getAll() {
		List<FaultData> result= new ArrayList<FaultData>();
	
	try {
		ResultSet rs= getAllFaultStatement.executeQuery();
		while(rs.next())
			result.add(new FaultData(rs.getString("description_fault"),rs.getString("date_solution_fault"),rs.getString("What_do")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
	// @Override fytj
	public boolean save(FaultData obj) {
		try {
			addFaultStatement.setString(1, obj.getDescription_fault());
			addFaultStatement.setString(2, obj.getDate_solution_fault());
			addFaultStatement.setString(3, obj.getWhat_do());
			
			return addFaultStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	// @Override fytj
	public boolean delete(FaultData obj) {
		try {
			deleteFaultStatement.setString(1, obj.getWhat_do());
			deleteFaultStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
//abstract
}
