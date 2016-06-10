package cdiofinal.client;

import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatches")
public interface ProduktBatchRPCInterface extends RemoteService{
	
	ProduktBatchDTO getProduktBatch(int pb_id);
	ProduktBatchDTO[] getProduktBatchList();
	Integer createProduktBatch(ProduktBatchDTO ans);
	Integer updateProduktBatch(ProduktBatchDTO ans);
}
