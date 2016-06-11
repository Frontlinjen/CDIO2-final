package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptDTO;

@RemoteServiceRelativePath("recept")
public interface ReceptRPCInterface extends RemoteService{
	ReceptDTO getRecept(int recId, String token) throws DALException;
	ReceptDTO[] getReceptList(String token) throws DALException;
	ReceptDTO createRecept(ReceptDTO rec, String token) throws DALException;
	Integer updateRecept(ReceptDTO rec, String token) throws DALException;
}
