package cdiofinal.client;

import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProduktBatchRPCInterfaceAsync {
	public void getProduktBatch(int pb_id, AsyncCallback<ProduktBatchDTO> callback);
	public void getProduktBatchList(AsyncCallback<ProduktBatchDTO[]> callback);
	public void createProduktBatch(ProduktBatchDTO pb, AsyncCallback<Integer> callback);
	public void updateProduktBatch(ProduktBatchDTO pb, AsyncCallback<Integer> callback);
}
