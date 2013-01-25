package Testpack1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

import servis.worker.WorkerData;
import servis.management.WorkerManagement;
import servis.management.ManagementInterface;

public class WorkerTest {
	
	WorkerManagement workertest = new WorkerManagement();
	
	private final static String name = "Name";
	private final static String surname = "SurName";
	private final static String address = "Address";
	private final static String phone = "Phone";
	
	@Test
	public void checkConnection(){
		assertNotNull(workertest.getConnection());
	}
	@Test
	public void checkAdding(){
		WorkerData worker007 = new WorkerData("name","surname","address","phone");
		
		workertest.save(worker007);
		
		List<WorkerData> worker2 = workertest.getAll();
		WorkerData personRetrieved = worker2.get(worker2.size()-1);
		
		assertEquals("name", personRetrieved.getName());
		assertEquals("surname",personRetrieved.getSurname());
		assertEquals("address",personRetrieved.getAddress());
		assertEquals("phone",personRetrieved.getPhone());
	}
	@Test
	public void checkDeleting(){
		WorkerManagement worker = new WorkerManagement();
		WorkerData work1 = new WorkerData(1);
		boolean deleted = worker.delete(work1);
		
		assertFalse("nie można skasować",deleted);
		
	}
	@Test 
	public void testGetAll(){
		WorkerManagement worker = new WorkerManagement();
		List<WorkerData> results = worker.getAll();
		
		assertNotNull("Lista jest pusta", results);
	}
	@Test
	public void testGet(){
		ManagementInterface<WorkerData> worker = new WorkerManagement();
		WorkerData result = worker.get(3);
		
		assertNotNull("brak wyników do wyświetlenia",result);
	}
}