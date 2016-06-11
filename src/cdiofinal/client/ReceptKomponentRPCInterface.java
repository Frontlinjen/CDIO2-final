package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

@RemoteServiceRelativePath("receptkomponent")
public interface ReceptKomponentRPCInterface {
	ReceptKompDTO getReceptKomp(int recId, int raavareId) throws DALException;
	ReceptKompDTO[] getReceptKompList() throws DALException;
	Integer createReceptKomp(ReceptKompDTO recKomp) throws DALException;
	Integer updateReceptKomp(ReceptKompDTO recKomp) throws DALException;
}
