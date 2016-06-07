package cdiofinal.client;

import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProduktBatchRPCInterfaceAsync {
	public void getProduktBatch(String cpr, AsyncCallback<ProduktBatchDTO> callback);
	public void  getProduktBatchList(AsyncCallback<ProduktBatchDTO[]> callback);
	public void createProduktBatch(ProduktBatchDTO user, AsyncCallback<Integer> callback);
	public void updateProduktBatch(ProduktBatchDTO user, AsyncCallback<Integer> callback);
	public void deleteProduktBatch(ProduktBatchDTO user, AsyncCallback<Integer> callback);

}
