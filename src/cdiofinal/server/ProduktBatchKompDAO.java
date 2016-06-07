package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.ProduktBatchKompDTO;

public interface ProduktBatchKompDAO {
	ProduktBatchKompDTO getProduktBatchKomp(int pbId, int raavarebatchId) throws DALException;
	List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
	void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
	void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;	
}

