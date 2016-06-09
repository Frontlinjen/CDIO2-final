package cdiofinal.client;

import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Recept og receptkomponent")
public interface RecRPCInterface {
	ReceptDTO getRecept(int recId);
	ReceptDTO[] getReceptList();
	Integer createRecept(ReceptDTO rec);
	Integer updateRecept(ReceptDTO rec);
	ReceptKompDTO getReceptKomp(int recId, int raavareId);
	ReceptKompDTO[] getReceptKompList();
	Integer createReceptKomp(ReceptKompDTO recKomp);
	Integer updateReceptKomp(ReceptKompDTO recKomp);
}
