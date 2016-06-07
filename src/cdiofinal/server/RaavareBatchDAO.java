package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.RaavareBatchDTO;

public interface RaavareBatchDAO {
	RaavareBatchDTO getRaavareBatch(int raavarebatchId) throws DALException;
	List<RaavareBatchDTO> getRaavarebatchList() throws DALException;
	void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
	void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
}
