package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptDTO;

@RemoteServiceRelativePath("recept")
public interface ReceptRPCInterface extends RemoteService{
	ReceptDTO getRecept(int recId) throws DALException;
	ReceptDTO[] getReceptList() throws DALException;
	Integer createRecept(ReceptDTO rec) throws DALException;
	Integer updateRecept(ReceptDTO rec) throws DALException;
}
