package cdiofinal.client;

import cdiofinal.shared.RaavareBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RaavareBatches")
public interface RaavareBatchRPCInterface extends RemoteService{
	
	RaavareBatchDTO getRaavareBatch(int rb_id);
	RaavareBatchDTO[] getRaavareBatchList();
	Integer createRaavareBatch(RaavareBatchDTO ans);
	Integer updateRaavareBatch(RaavareBatchDTO ans);
	Integer deleteRaavareBatch(RaavareBatchDTO ans);
}
