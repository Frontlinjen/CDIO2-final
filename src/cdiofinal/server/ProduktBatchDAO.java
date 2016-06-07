package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.ProduktBatchDTO;

public interface ProduktBatchDAO {
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	void deleteProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}