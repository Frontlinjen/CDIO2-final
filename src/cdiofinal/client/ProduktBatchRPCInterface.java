package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatches")
public interface ProduktBatchRPCInterface extends RemoteService{
	
	ProduktBatchDTO getProduktBatch(int pb_id, String token) throws Exception;
	ProduktBatchDTO[] getProduktBatchList(String token) throws Exception;
	ProduktBatchDTO createProduktBatch(ProduktBatchDTO ans, String token) throws Exception;
	Integer updateProduktBatch(ProduktBatchDTO ans, String token) throws Exception;
}
