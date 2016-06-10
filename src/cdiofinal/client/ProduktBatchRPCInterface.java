package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatches")
public interface ProduktBatchRPCInterface extends RemoteService{
	
	ProduktBatchDTO getProduktBatch(int pb_id) throws DALException;
	ProduktBatchDTO[] getProduktBatchList();
	Integer createProduktBatch(ProduktBatchDTO ans) throws DALException;
	Integer updateProduktBatch(ProduktBatchDTO ans) throws DALException;
}
