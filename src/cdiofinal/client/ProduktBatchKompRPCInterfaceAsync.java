package cdiofinal.client;

import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProduktBatchKompRPCInterfaceAsync {
	public void getProduktBatchKomp(int pb_id, int rabaid, AsyncCallback<ProduktBatchKompDTO> callback);
	public void getProduktBatchKompList(AsyncCallback<ProduktBatchKompDTO[]> callback);
	public void createProduktBatchKomp(ProduktBatchKompDTO pbk, AsyncCallback<Integer> callback);
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbk, AsyncCallback<Integer> callback);
	public void deleteProduktBatchKomp(ProduktBatchKompDTO pbk, AsyncCallback<Integer> callback);
}
