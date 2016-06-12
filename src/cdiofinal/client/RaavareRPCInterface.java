package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("raavare")
public interface RaavareRPCInterface extends RemoteService{
	RaavareDTO getRaavare(int raavareId, String token) throws Exception;
	RaavareDTO[] getRaavareList(String token) throws Exception;
	RaavareDTO createRaavare(RaavareDTO raa, String token) throws Exception;
	Integer updateRaavare(RaavareDTO raa, String token) throws Exception;
}