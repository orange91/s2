package servis.core;


import servis.client.ClientData;
import servis.worker.WorkerData;
import servis.electronics.ElectronicsData;
import servis.fault.FaultData;
import servis.management.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagementInterface<ClientData> client = new ClientManagement();
	
	//	client.save(new ClientData("Jan","Kowalski","świętego spokoju 5", "700"));
		
	}

}
