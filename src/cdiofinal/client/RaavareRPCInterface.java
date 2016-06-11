package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("raavare")
public interface RaavareRPCInterface extends RemoteService{
	RaavareDTO getRaavare(int raavareId) throws DALException;
	RaavareDTO[] getRaavareList() throws DALException;
	Integer createRaavare(RaavareDTO raa) throws DALException;
	Integer updateRaavare(RaavareDTO raa) throws DALException;
}