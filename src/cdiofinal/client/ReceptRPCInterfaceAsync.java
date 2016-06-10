package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.ReceptDTO;

public interface ReceptRPCInterfaceAsync {
	public void getRecept(int recId, AsyncCallback<ReceptDTO> callback);
	public void getReceptList(AsyncCallback<ReceptDTO[]> callback);
	public void createRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void updateRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
}
