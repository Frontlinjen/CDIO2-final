package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdiofinal.shared.ReceptKompDTO;

public interface ReceptKomponentRPCInterfaceAsync {
	public void getReceptKomp(int recId, int raaId, String token, AsyncCallback<ReceptKompDTO> callback);
	public void getReceptKompList(int recept, String token, AsyncCallback<ReceptKompDTO[]> callback);
	public void createReceptKomp(ReceptKompDTO recKomp, String token, AsyncCallback<ReceptKompDTO> callback);
	public void updateReceptKomp(ReceptKompDTO recKomp, String token, AsyncCallback<Integer> callback);
}
