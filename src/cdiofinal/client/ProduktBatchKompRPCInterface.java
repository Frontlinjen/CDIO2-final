package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatchKomponenter")
public interface ProduktBatchKompRPCInterface extends RemoteService{
	
	ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid, String token) throws DALException;
	ProduktBatchKompDTO[] getProduktBatchKompList(String token) throws DALException;
	ProduktBatchKompDTO createProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws DALException;
	Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws DALException;
}
