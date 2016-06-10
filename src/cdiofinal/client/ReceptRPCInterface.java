package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.ReceptDTO;

@RemoteServiceRelativePath("recept")
public interface ReceptRPCInterface {
	ReceptDTO getRecept(int recId);
	ReceptDTO[] getReceptList();
	Integer createRecept(ReceptDTO rec);
	Integer updateRecept(ReceptDTO rec);
}
