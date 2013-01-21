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
Connection conn;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable =
			";
 
	Statement statement;
	PreparedStatement addClientDataStatement;
	PreparedStatement deleteClientDataStatement;
	PreparedStatement getAllClientDataStatement;
	PreparedStatement getClientDataByIdStatement;
	
	public ClientManagement() {
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

			addClientDataStatement = conn
					.prepareStatement(
							"INSERT INTO Pacjent (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteClientDataStatement=conn
					.prepareStatement("delete from client where surname=?");
			getAllClientDataStatement = conn
					.prepareStatement("select * from client");
			getClientDataByIdStatement=conn
					.prepareStatement("select * from client where client=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	
	// @Override fytj
	public ClientData get(long id) {
	
	ClientData result = null;
		
	try {
		getClientDataByIdStatement.setLong(1, id);
		ResultSet rs = getClientDataByIdStatement.executeQuery();
		while(rs.next()){
			result = new ClientData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getInt("phone"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	// @Override fytj
	public List<ClientData> getAll() {
		List<ClientData> result= new ArrayList<ClientData>();
	
	try {
		ResultSet rs= getAllClientDataStatement.executeQuery();
		while(rs.next())
			result.add(new ClientData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getInt("phone")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
	// @Override fytj
	public boolean save(ClientData obj) {
		try {
			addClientDataStatement.setString(1, obj.getName());
			addClientDataStatement.setString(2, obj.getSurname());
			addClientDataStatement.setString(3, obj.getAddress());
			addClientDataStatement.setInt(4, obj.getPhone());
			return addClientDataStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	// @Override fytj
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
