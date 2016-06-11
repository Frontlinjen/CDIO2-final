package cdiofinal.client;

import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LeverandoerRPCInterfaceAsync {
	public void getLeverandoer(int rvId, String token, AsyncCallback<LeverandoerDTO> callback);
	public void getLeverandoerList(String token, AsyncCallback<LeverandoerDTO[]> callback);
	public void createLeverandoer(LeverandoerDTO lev, String token, AsyncCallback<LeverandoerDTO> callback);
	public void updateLeverandoer(LeverandoerDTO lev, String token, AsyncCallback<Integer> callback);
}