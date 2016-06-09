package cdiofinal.client;

import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LeverandoerRPCInterfaceAsync {
	public void getLeverandoer(int rvId, AsyncCallback<LeverandoerDTO> callback);
	public void getLeverandoerList(AsyncCallback<LeverandoerDTO[]> callback);
	public void createLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
	public void updateLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
	public void deleteLeverandoer(LeverandoerDTO lev, AsyncCallback<Integer> callback);
}