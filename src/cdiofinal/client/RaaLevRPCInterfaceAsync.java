package cdiofinal.client;


import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RaaLevRPCInterfaceAsync {
	public void getRaavare(int raavareId, AsyncCallback<RaavareDTO> callback);
	public void getRaavareList(AsyncCallback<RaavareDTO[]> callback);
	public void createRaavare(RaavareDTO raa, AsyncCallback<Integer> callback);
	public void updateRaavare(RaavareDTO raa, AsyncCallback<Integer> callback);
	public void deleteRaavare(RaavareDTO raa, AsyncCallback<Integer> callback);
	public void getLeverandoer(int rvId, AsyncCallback<LeverandoerDTO> callback);
	public void getLeverandoerList(AsyncCallback<LeverandoerDTO[]> callback);
	public void createLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
	public void updateLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
	public void deleteLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
}
