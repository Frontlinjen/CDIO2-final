package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.ReceptKompDTO;

public interface ReceptKomponentRPCInterfaceAsync {
	public void getReceptKomp(int recId, int raaId, AsyncCallback<ReceptKompDTO> callback);
	public void getReceptKompList(AsyncCallback<ReceptKompDTO[]> callback);
	public void createReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
	public void updateReceptKomp(ReceptKompDTO recKomp, AsyncCallback<Integer> callback);
}
