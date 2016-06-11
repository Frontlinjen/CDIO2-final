package cdiofinal.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

@RemoteServiceRelativePath("receptkomponent")
public interface ReceptKomponentRPCInterface {
	ReceptKompDTO getReceptKomp(int recId, int raavareId, String token) throws DALException;
	ReceptKompDTO[] getReceptKompList(String token) throws DALException;
	ReceptKompDTO createReceptKomp(ReceptKompDTO recKomp, String token) throws DALException;
	Integer updateReceptKomp(ReceptKompDTO recKomp, String token) throws DALException;
}
