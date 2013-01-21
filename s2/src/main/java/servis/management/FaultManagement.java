package servis.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servis.electronics.Fault;





public class FaultManagement implements ManagementInterface<Fault> {
Connection conn;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable ="";
			
 
	Statement statement;
	PreparedStatement addFaultStatement;
	PreparedStatement deleteFaultStatement;
	PreparedStatement getAllFaultStatement;
	PreparedStatement getFaultByIdStatement;
	
	public FaultManagement() {
		try {
			conn = DriverManager.getConnection(url, login, password);
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("client".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if(!tableExists)
				statement.executeUpdate(createTable);

			addFaultStatement = conn
					.prepareStatement(
							"INSERT INTO Pacjent (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteFaultStatement=conn
					.prepareStatement("delete from client where surname=?");
			getAllFaultStatement = conn
					.prepareStatement("select * from client");
			getFaultByIdStatement=conn
					.prepareStatement("select * from client where client=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return conn;
	}
	/*
	 this.description_fault = description_fault;
		this.date_solution_fault =  date_solution_fault;
		this.what_do = what_do;
		
		
	 * (non-Javadoc)
	 * @see servis.management.ManagementInterface#get(long)
	 */
	
	// @Override fytj
	public Fault get(long id) {
	
	Fault result = null;
		
	try {
		getFaultByIdStatement.setLong(1, id);
		ResultSet rs = getFaultByIdStatement.executeQuery();
		while(rs.next()){
			result = new Fault(rs.getString("description_fault"),rs.getString("date_solution_fault"),rs.getString("what_do"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	// @Override fytj
	public List<Fault> getAll() {
		List<Fault> result= new ArrayList<Fault>();
	
	try {
		ResultSet rs= getAllFaultStatement.executeQuery();
		while(rs.next())
			result.add(new Fault(rs.getString("description_fault"),rs.getString("date_solution_fault"),rs.getString("What_do")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
	// @Override fytj
	public boolean save(Fault obj) {
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
	public boolean delete(Fault obj) {
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
