package cdiofinal.client;

import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatchKomponenter")
public interface ProduktBatchKompRPCInterface extends RemoteService{
	
	ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid, String token) throws Exception;
	ProduktBatchKompDTO[] getProduktBatchKompList(String token) throws Exception;
	ProduktBatchKompDTO createProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception;
	Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception;
}
