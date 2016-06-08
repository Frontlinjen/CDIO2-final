package cdiofinal.client;

import cdiofinal.shared.ReceptDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReceptRPCInterfaceAsync {
	public void getRecept(int recId, AsyncCallback<ReceptDTO> callback);
	public void getReceptList(AsyncCallback<ReceptDTO[]> callback);
	public void createRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void updateRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void deleteRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
}
