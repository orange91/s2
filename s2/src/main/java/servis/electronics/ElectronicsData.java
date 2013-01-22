package servis.electronics;
/*
import java.util.Date;



import servis.client.ClientData;
import servis.worker.WorkerData;
import servis.electronics.Fault;
*/

public class ElectronicsData {
		/*
		 CREATE TABLE electronics
(   
    id_electronics   serial,
    nr_serial        varchar(20)    not null,
    model            varchar(20)    not null,
    extend           varchar(200),
    e_name           varchar(30)    not null,
    id_client		 int 			not null,
	id_worker 		 int 			not null, 
	id_fault		 int			not null,
    CONSTRAINT        electronics_nr_serial_pk PRIMARY KEY(nr_serial),
   // CONSTRAINT              firma_fk FOREIGN KEY(nazwa) REFERENCES firma(nazwa),
    CONSTRAINT  fault_fk FOREIGN KEY(id_fault) REFERENCES fault(id_fault),
    CONSTRAINT id_client_fk FOREIGN KEY(id_client) REFERENCES client(id) ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT id_worker_fk FOREIGN KEY(id_worker) REFERENCES worker(id) ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT id_fault_fk FOREIGN KEY(id_fault) REFERENCES fault(id) ON UPDATE CASCADE ON DELETE SET NULL;
		 */
	
	/*
 	id				 serial,
    nr_serial        varchar(20)    not null,
    model            varchar(20)    not null,
    extend           varchar(200),
    e_name           varchar(30)    not null,
    id_client		 int 			not null,
	id_worker 		 int 			not null, 
	id_fault		 int			not null,
 */
	private long id;//long id
	private String nr_serial;
	private String model;
	private String e_name;
	private int id_client;
	private int id_worker;
	private int id_fault;
		
		public ElectronicsData(long id) {
			this.id = id;
		}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public ElectronicsData(String nr_serial, String model, String e_name, int id_client, int id_fault, int id_worker) {
			this.nr_serial = nr_serial;
			this.model = model;
			this.e_name = e_name;
			this.id_client = id_client;
			this.id_fault = id_fault;
			this.id_worker = id_worker;
		}

		public int getId_client(){
			return id_client;
		}
		public void setId_client(int id_client){
			this.id_client = id_client;
		}
		public int getId_worker(){
			return id_worker;
		}
		public void setId_worker(int id_worker){
			this.id_worker = id_worker;
		}
		public int getId_fault(){
			return id_fault;
		}
		public void setId_fault(int id_fault){
			this.id_fault = id_fault;
		}
		public String getNr_serial() {
			return nr_serial;
		}
		public void setNr_serial(String nr_serial) {
			this.nr_serial = nr_serial;
		}
		public String getE_name() {
			return e_name;
		}
		public void setE_name(String e_name) {
			this.e_name = e_name;
		}		
		public String getModel(){
			return model;
		}
		public void setModel(String model){
			this.model = model;
		}
	}

