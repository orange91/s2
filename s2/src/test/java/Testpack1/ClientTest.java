package Testpack1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;

import servis.client.ClientData;
import servis.management.ClientManagement;
import servis.management.ManagementInterface;
import servis.management.WorkerManagement;
import servis.worker.WorkerData;

public class ClientTest {
	ClientManagement clienttest = new ClientManagement();
	private final static String name = "Name";
	private final static String surname = "SurName";
	private final static String address = "Address";
	private final static String phone = "Phone";
	
	
	@Test
	public void checkConnection(){
		assertNotNull(clienttest.getConnection());
	}
	@Test
	public void checkAdding(){
		ClientData client = new ClientData("name","surname","address","phone");
		
		clienttest.save(client);
		
		List<ClientData> client2 = clienttest.getAll();
		ClientData personRetrieved = client2.get(client2.size()-1);
		
		assertEquals("name", personRetrieved.getName());
		assertEquals("surname",personRetrieved.getSurname());
		assertEquals("address",personRetrieved.getAddress());
		assertEquals("phone",personRetrieved.getPhone());
	}
	
	@Test
	public void checkDeleting1(){
		ClientManagement client = new ClientManagement();
		ClientData clie1 = new ClientData(7);
		boolean deleted = client.delete(clie1);
		
		assertFalse("nie można skasować",deleted);
	}
	@Test 
	public void testGetAll(){
		ClientManagement client3 = new ClientManagement();
		List<ClientData> results = client3.getAll();
		
		assertNotNull("Lista jest pusta", results);
	}
	@Test
	public void testGet(){
		ManagementInterface<ClientData> client4 = new ClientManagement();
		ClientData result = client4.get(9);
		
		assertNotNull("brak wyników do wyświetlenia",result);
	}
}
