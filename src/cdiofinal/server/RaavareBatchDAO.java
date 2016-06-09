package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.RaavareBatchDTO;

public interface RaavareBatchDAO {
	RaavareBatchDTO getRaavareBatch(int raavarebatchId) throws DALException;
	List<RaavareBatchDTO> getRaavarebatchList() throws DALException;
	int createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
	int updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
}
