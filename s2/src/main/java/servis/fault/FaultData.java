package servis.fault;

/*
import java.util.Date;



import servis.client.ClientData;
import servis.worker.WorkerData;
import servis.electronics.ElectronicsData;
*/
public class FaultData {
	
	/*
	 CREATE TABLE fault
(
    id_fault        		serial,
    description_fault       varchar(500)    not null,
    date_solution_fault    	varchar(20),
    what_do       			varchar(300),
    extend            		varchar(200),
    CONSTRAINT        fault_id_fault_pk PRIMARY KEY(id_fault)
);
	 */
	private long id;
	private String description_fault;
	private String date_solution_fault;
	private String what_do;
	
	
	public FaultData(String description_fault, String date_solution_fault, String what_do) {
		this.description_fault = description_fault;
		this.date_solution_fault =  date_solution_fault;
		this.what_do = what_do;
		
	}
	
	public FaultData(long id) {
		this.id = id;
	}
	
	public long getIdFault() {
		return id;
	}
	public void setIdFault(int id) {
		this.id = id; 
	}
	public String getDescription_fault() {
		return description_fault;
	}
	public void setDescription_fault(String description_fault) {
		this.description_fault = description_fault;
	}
	public String getDate_solution_fault() {
		return date_solution_fault;
	}
	public void setDate_solution_fault(String date_solution_fault) {
		this.date_solution_fault = date_solution_fault;
	}
	public String getWhat_do() {
		return what_do;
	}
	public void setWhat_do(String what_do) {
		this.what_do = what_do;
	}}
