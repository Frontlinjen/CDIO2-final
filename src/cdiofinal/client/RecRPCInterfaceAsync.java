package cdiofinal.client;

import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecRPCInterfaceAsync {
	public void getRecept(int recId, AsyncCallback<ReceptDTO> callback);
	public void getReceptList(AsyncCallback<ReceptDTO[]> callback);
	public void createRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void updateRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void deleteRecept(ReceptDTO rec, AsyncCallback<Integer> callback);
	public void getReceptKomp(int recId, int raaId, AsyncCallback<ReceptKompDTO> callback);
	public void getReceptKompList(AsyncCallback<ReceptKompDTO[]> callback);
	public void createReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
	public void updateReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
	public void deleteReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
}
