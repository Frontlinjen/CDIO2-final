package cdiofinal.client;

import cdiofinal.shared.ReceptKompDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ReceptKomp")
public interface ReceptKompIRPCInterface {
	ReceptKompDTO getRecept(int receptId, int raavareId);
	ReceptKompDTO[] getReceptKompList();
	Integer createReceptKomp(ReceptKompDTO recKomp);
	Integer updateReceptKomp(ReceptKompDTO recKomp);
	Integer deleteReceptKomp(ReceptKompDTO recKomp);
}
