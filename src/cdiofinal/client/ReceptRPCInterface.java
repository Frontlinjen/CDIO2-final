package cdiofinal.client;

import cdiofinal.shared.ReceptDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Recept")
public interface ReceptRPCInterface {
	ReceptDTO getRecept(int recId);
	ReceptDTO[] getReceptList();
	Integer createRecept(ReceptDTO rec);
	Integer updateRecept(ReceptDTO rec);
	Integer deleteRecept(ReceptDTO rec);
}
