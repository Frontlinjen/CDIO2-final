package cdiofinal.client;

import cdiofinal.shared.ReceptKompDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReceptKompRPCInterfaceAsync {
	public void getReceptKomp(int recId, int raaId, AsyncCallback<ReceptKompDTO> callback);
	public void getReceptKompList(AsyncCallback<ReceptKompDTO[]> callback);
	public void createReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
	public void updateReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
	public void deleteReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
}
