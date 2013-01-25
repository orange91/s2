

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

import servis.client.ClientData;
import servis.management.ClientManagement;
import servis.management.ManagementInterface;

public class ClientTest {
	ClientManagement clienttest = new ClientManagement();
	
	public void checkConnection(){
		assertNotNull(clienttest.getConnection());
	}
	
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
}
