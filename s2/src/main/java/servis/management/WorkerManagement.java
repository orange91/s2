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
Connection conn;
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String password = "postgres";
	private String login = "postgres";

	String createTable =
			";
 
	Statement statement;
	PreparedStatement addWorkerDataStatement;
	PreparedStatement deleteWorkerDataStatement;
	PreparedStatement getAllWorkerDataStatement;
	PreparedStatement getWorkerDataByIdStatement;
	
	public WorkerManagement() {
		try {
			conn = DriverManager.getConnection(url, login, password);
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("Worker".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if(!tableExists)
				statement.executeUpdate(createTable);

			addWorkerDataStatement = conn
					.prepareStatement(
							"INSERT INTO Pacjent (name, surname, address, phone) " +
							"VALUES (?, ?, ?, ?)");
			deleteWorkerDataStatement=conn
					.prepareStatement("delete from Worker where surname=?");
			getAllWorkerDataStatement = conn
					.prepareStatement("select * from Worker");
			getWorkerDataByIdStatement=conn
					.prepareStatement("select * from Worker where Worker=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	
	// @Override fytj
	public WorkerData get(long id) {
	
	WorkerData result = null;
		
	try {
		getWorkerDataByIdStatement.setLong(1, id);
		ResultSet rs = getWorkerDataByIdStatement.executeQuery();
		while(rs.next()){
			result = new WorkerData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getInt("phone"));
			break;
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}	

	// @Override fytj
	public List<WorkerData> getAll() {
		List<WorkerData> result= new ArrayList<WorkerData>();
	
	try {
		ResultSet rs= getAllWorkerDataStatement.executeQuery();
		while(rs.next())
			result.add(new WorkerData(rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getInt("phone")));
		
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
	// @Override fytj
	public boolean save(WorkerData obj) {
		try {
			addWorkerDataStatement.setString(1, obj.getName());
			addWorkerDataStatement.setString(2, obj.getSurname());
			addWorkerDataStatement.setString(3, obj.getAddress());
			addWorkerDataStatement.setInt(4, obj.getPhone());
			return addWorkerDataStatement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	// @Override fytj
	public boolean delete(WorkerData obj) {
		try {
			deleteWorkerDataStatement.setString(1, obj.getAddress());
			deleteWorkerDataStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
//abstract
}
