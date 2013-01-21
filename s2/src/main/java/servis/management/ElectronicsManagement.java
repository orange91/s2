package servis.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servis.electronics.ElectronicsData;

public class ElectronicsManagement implements ManagementInterface<ElectronicsData>{

/*
 * id_electronics   serial,
    nr_serial        varchar(20)    not null,
    model            varchar(20)    not null,
    extend           varchar(200),
    e_name           varchar(30)    not null,
    id_client		 int 			not null,
	id_worker 		 int 			not null, 
	id_fault		 int			not null,
 */


Connection conn;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable ="";
			
	Statement statement;
	PreparedStatement addElectronicsDataStatement;
	PreparedStatement deleteElectronicsDataStatement;
	PreparedStatement getAllElectronicsDataStatement;
	PreparedStatement getElectronicsDataByIdStatement;
	
	public ElectronicsManagement() {
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

			addElectronicsDataStatement = conn
					.prepareStatement(
							"INSERT INTO Pacjent (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteElectronicsDataStatement=conn
					.prepareStatement("delete from client where surname=?");
			getAllElectronicsDataStatement = conn
					.prepareStatement("select * from client");
			getElectronicsDataByIdStatement=conn
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
	
	/*
	 * id_electronics   serial,
	    nr_serial        varchar(20)    not null,
	    model            varchar(20)    not null,
	    extend           varchar(200),
	    e_name           varchar(30)    not null,
	    id_client		 int 			not null,
		id_worker 		 int 			not null, 
		id_fault		 int			not null,
	 */
	
	// @Override fytj
	public ElectronicsData get(long id) {
	
		ElectronicsData result = null;
		
	try {
		getElectronicsDataByIdStatement.setLong(1, id);
		ResultSet rs = getElectronicsDataByIdStatement.executeQuery();
		while(rs.next()){
			result = new ElectronicsData(rs.getString("nr_serial"),rs.getString("model"),rs.getString("e_name"),rs.getInt("id_client"),rs.getInt("id_worker"),rs.getInt("id_fault"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	// @Override fytj
	public List<ElectronicsData> getAll() {
		List<ElectronicsData> result= new ArrayList<ElectronicsData>();
	
	try {
		ResultSet rs= getAllElectronicsDataStatement.executeQuery();
		while(rs.next())
			result.add(new ElectronicsData(rs.getString("nr_serial"),rs.getString("model"),rs.getString("e_name"),rs.getInt("id_client"),rs.getInt("id_worker"),rs.getInt("id_fault")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
	// @Override fytj
	public boolean save(ElectronicsData obj) {
		try {
			addElectronicsDataStatement.setString(1, obj.getNr_serial());
			addElectronicsDataStatement.setString(2, obj.getModel());
			addElectronicsDataStatement.setString(3, obj.getE_name());
			addElectronicsDataStatement.setInt(4, obj.getId_client());
			addElectronicsDataStatement.setInt(5, obj.getId_worker());
			addElectronicsDataStatement.setInt(5, obj.getId_fault());
			return addElectronicsDataStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	// @Override fytj
	public boolean delete(ElectronicsData obj) {
		try {
			deleteElectronicsDataStatement.setString(1, obj.getE_name());
			deleteElectronicsDataStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
//abstract
}
