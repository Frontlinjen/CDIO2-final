package cdiofinal.client;


import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RaavareRPCInterfaceAsync {
	public void getRaavare(int raavareId, AsyncCallback<RaavareDTO> callback);
	public void getRaavareList(AsyncCallback<RaavareDTO[]> callback);
	public void createRaavare(RaavareDTO raa, AsyncCallback<Integer> callback);
	public void updateRaavare(RaavareDTO raa, AsyncCallback<Integer> callback);
}