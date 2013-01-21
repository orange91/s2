package servis.management;

import java.util.List;
//import servis.client.*;

public interface ManagementInterface<TEntity> {
	
	public TEntity get(long id);
	public List<TEntity> getAll();
	public boolean save(TEntity obj);
	public boolean delete(TEntity obj);
	//ClientData getIdClient(long id_client);

}
