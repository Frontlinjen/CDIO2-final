package cdiofinal.client;

import cdiofinal.shared.RaavareBatchDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RaavareBatchRPCInterfaceAsync {
	public void getRaavareBatch(int rb_id, String token AsyncCallback<RaavareBatchDTO> callback);
	public void getRaavareBatchList(String token, AsyncCallback<RaavareBatchDTO[]> callback);
	public void createRaavareBatch(RaavareBatchDTO pb, String token, AsyncCallback<Integer> callback);
	public void updateRaavareBatch(RaavareBatchDTO pb, String token, AsyncCallback<Integer> callback);
}
