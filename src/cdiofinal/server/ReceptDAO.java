package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	int createRecept(ReceptDTO recept) throws DALException;
	int updateRecept(ReceptDTO recept) throws DALException;
}
