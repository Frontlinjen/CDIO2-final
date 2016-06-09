package cdiofinal.client;

import cdiofinal.shared.ReceptKompDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReceptKompRPCInterfaceAsync {
	public void getReceptKomp(int receptId, int raavareId, AsyncCallback<ReceptKompDTO> callback);
	public void getReceptKompList(AsyncCallback<ReceptKompDTO[]> callback);
	public void createReceptKomp(ReceptKompDTO rec, AsyncCallback<Integer> callback);
	public void updateReceptKomp(ReceptKompDTO rec, AsyncCallback<Integer> callback);
	public void deleteReceptKomp(ReceptKompDTO rec, AsyncCallback<Integer> callback);
}
