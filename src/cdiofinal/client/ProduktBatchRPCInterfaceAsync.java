package cdiofinal.client;

import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProduktBatchRPCInterfaceAsync {
	public void getProduktBatch(int pb_id, String token, AsyncCallback<ProduktBatchDTO> callback);
	public void getProduktBatchList(String token, AsyncCallback<ProduktBatchDTO[]> callback);
	public void createProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback<ProduktBatchDTO> callback);
	public void updateProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback<Integer> callback);
}
