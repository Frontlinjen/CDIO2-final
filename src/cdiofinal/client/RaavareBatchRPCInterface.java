package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.RaavareBatchDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("raavareBatch")
public interface RaavareBatchRPCInterface extends RemoteService{
	
	RaavareBatchDTO getRaavareBatch(int rb_id, String token) throws Exception;
	RaavareBatchDTO[] getRaavareBatchList(String token) throws Exception;
	RaavareBatchDTO createRaavareBatch(RaavareBatchDTO ans, String token) throws Exception;
	Integer updateRaavareBatch(RaavareBatchDTO ans, String token) throws Exception;
}
