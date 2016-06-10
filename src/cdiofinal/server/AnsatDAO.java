package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

public interface AnsatDAO {
	AnsatDTO getAnsat(String cpr) throws DALException;
	List<AnsatDTO> getAnsatList() throws DALException;
	int createAnsat(AnsatDTO ans) throws DALException;
	int updateAnsat(AnsatDTO ans) throws DALException;
	int deleteAnsat(AnsatDTO ans) throws DALException;
}
