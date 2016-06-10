package cdiofinal.client;

import cdiofinal.shared.ProduktBatchKompDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("produktBatchKomponenter")
public interface ProduktBatchKompRPCInterface extends RemoteService{
	
	ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid);
	ProduktBatchKompDTO[] getProduktBatchKompList();
	Integer createProduktBatchKomp(ProduktBatchKompDTO pbk);
	Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk);
}
