package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.ReceptDTO;

public interface ReceptRPCInterfaceAsync {
	public void getRecept(int recId, String token, AsyncCallback<ReceptDTO> callback);
	public void getReceptList(String token, AsyncCallback<ReceptDTO[]> callback);
	public void createRecept(ReceptDTO rec, String token, AsyncCallback<ReceptDTO> callback);
	public void updateRecept(ReceptDTO rec, String token, AsyncCallback<Integer> callback);
}
