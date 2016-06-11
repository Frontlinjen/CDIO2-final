package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ansatte")
public interface AnsatRPCInterface extends RemoteService{
	AnsatDTO getAnsat(String cpr, String token) throws DALException;
	AnsatDTO[] getAnsatList(String token) throws DALException;
	AnsatDTO createAnsat(AnsatDTO ans, String token) throws DALException;
	Integer updateAnsat(AnsatDTO ans, String token) throws DALException;
	Integer deleteAnsat(AnsatDTO ans, String token) throws DALException;
}
