package cdiofinal.client;

import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProduktBatchKompRPCInterfaceAsync {
	public void getProduktBatchKomp(int pb_id, int rabaid, String token, AsyncCallback<ProduktBatchKompDTO> callback);
	public void getProduktBatchKompList(String token, AsyncCallback<ProduktBatchKompDTO[]> callback);
	public void createProduktBatchKomp(ProduktBatchKompDTO pbk, String token, AsyncCallback<Integer> callback);
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token, AsyncCallback<Integer> callback);
}
