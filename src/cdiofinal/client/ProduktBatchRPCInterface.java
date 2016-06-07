package cdiofinal.client;

import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ProduktBatch")
public interface ProduktBatchRPCInterface extends RemoteService{
	
	ProduktBatchDTO getProduktBatch(String pb_id);
	ProduktBatchDTO[] getProduktBatchList();
	Integer createProduktBatch(ProduktBatchDTO ans);
	Integer updateProuktBatch(ProduktBatchDTO ans);
	Integer deleteProduktBatch(ProduktBatchDTO ans);
}
