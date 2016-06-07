package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ProductBatch")
public interface ProductBatchRPCInterface extends RemoteService{
	
	ProductBatchDTO getProductBatch(String)
	
}
