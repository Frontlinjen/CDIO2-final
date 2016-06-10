package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.AnsatDTO;

public interface AnsatDAO {
	AnsatDTO getAnsat(int cpr) throws DALException;
	List<AnsatDTO> getAnsatList() throws DALException;
	int createAnsat(AnsatDTO ans) throws DALException;
	int updateAnsat(AnsatDTO ans) throws DALException;
	int deleteAnsat(AnsatDTO ans) throws DALException;
}
