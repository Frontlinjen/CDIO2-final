package cdiofinal.client;


import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RaavareRPCInterfaceAsync {
	public void getRaavare(int raavareId, String token AsyncCallback<RaavareDTO> callback);
	public void getRaavareList(String token, AsyncCallback<RaavareDTO[]> callback);
	public void createRaavare(RaavareDTO raa, String token, AsyncCallback<Integer> callback);
	public void updateRaavare(RaavareDTO raa, String token, AsyncCallback<Integer> callback);
}