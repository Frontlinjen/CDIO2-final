package cdiofinal.client;

import cdiofinal.shared.ReceptKompDTO;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ReceptKomp")
public interface ReceptKompIRPCInterface {
	ReceptKompDTO getReceptKomp(int recId, int raavareId);
	ReceptKompDTO[] getReceptKompList();
	Integer createReceptKomp(ReceptKompDTO recKomp);
	Integer updateReceptKomp(ReceptKompDTO recKomp);
	Integer deleteReceptKomp(ReceptKompDTO recKomp);
}
