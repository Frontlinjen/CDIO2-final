package cdiofinal.client;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatchKomponenter")
public interface ProduktBatchKompRPCInterface extends RemoteService{
	
	ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid) throws DALException;
	ProduktBatchKompDTO[] getProduktBatchKompList();
	Integer createProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException;
	Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException;
}
