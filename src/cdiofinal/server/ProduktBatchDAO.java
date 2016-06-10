package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;

public interface ProduktBatchDAO {
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	int createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	int updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}