package cdiofinal.client;

import cdiofinal.shared.RaavareBatchDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RaavareBatchRPCInterfaceAsync {
	public void getRaavareBatch(int rb_id, AsyncCallback<RaavareBatchDTO> callback);
	public void getRaavareBatchList(AsyncCallback<RaavareBatchDTO[]> callback);
	public void createRaavareBatch(RaavareBatchDTO pb, AsyncCallback<Integer> callback);
	public void updateRaavareBatch(RaavareBatchDTO pb, AsyncCallback<Integer> callback);
	public void deleteRaavareBatch(RaavareBatchDTO pb, AsyncCallback<Integer> callback);

}
