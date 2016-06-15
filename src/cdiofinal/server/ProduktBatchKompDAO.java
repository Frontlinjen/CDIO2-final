package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchKompDTO;

public interface ProduktBatchKompDAO {
	ProduktBatchKompDTO getProduktBatchKomp(int pbId, int raavarebatchId) throws DALException;
	List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
	int createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
	int updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
	List<ProduktBatchKompDTO> getProduktBatchKompList(int pdBatchID) throws DALException;	
}

