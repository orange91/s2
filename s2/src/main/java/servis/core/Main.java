package servis.core;


import servis.client.ClientData;
import servis.worker.WorkerData;
import servis.electronics.ElectronicsData;
import servis.fault.FaultData;
import servis.management.*;


public class Main {

	/**
	 * @param argsne
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagementInterface<ClientData> client = new ClientManagement();
		ManagementInterface<WorkerData> worker = new WorkerManagement();
		ManagementInterface<FaultData> fault = new FaultManagement();
		ManagementInterface<ElectronicsData> electronics = new ElectronicsManagement();
		
		
		client.save(new ClientData("Jan","Kowalski","świętego spokoju 5", "700700700"));
		client.save(new ClientData("Anna", "Kalinowska","ul. Profesora Towarnickiego 22","781992442"));
		client.save(new ClientData("Tomasz","Wiśniewski","ul. Kolonijna 20","607153532"));
		client.save(new ClientData("Kazimierz","Pawłowski","ul. Konrada Wallenroda 166","512630030"));
		
		
		worker.save(new WorkerData("Przemek","Szczepański","ul. Wiśniowa 39","603516411"));
		worker.save(new WorkerData("Krystian","Michalski","ul. Słowackiego 83","886761585"));
		worker.save(new WorkerData("Łukasz","Czerwiński","ul. Kielecka 124","512428745"));
		//description_fault, String date_solution_fault, String what_do
		fault.save(new FaultData("Zbita matryca laptopa","2012-12-12","Matryca została wymieniona na nową oryginalną"));
		fault.save(new FaultData("Uszkodzone porty USB","2012-12-21","Zamontowano nowe proty USB"));
		fault.save(new FaultData("Niestabilne działanie systemu operacyjnego","2013-01-03","Przeskanowano dyski w poszukiwaniu wirusów, błędów plików, oraz sprawdzono pamięć RAM"));
		//(nr_serial, model, e_name, id_client, id_worker, id_fault)
		electronics.save(new ElectronicsData("gf5fs445","SX-394","ASUS", 9 , 1 , 1));
		electronics.save(new ElectronicsData("1233434","Xperia U","Sony", 10, 2, 2));
		
	//	ClientData client1 = new ClientData(6);
	//	client.delete(client1);
		
		FaultData fault1 = new FaultData(32);
		fault.delete(fault1);
		
	}

}
