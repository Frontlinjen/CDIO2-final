package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ansatte")
public interface AnsatRPCInterface extends RemoteService{
	AnsatDTO getAnsat(String cpr) throws DALException;
	AnsatDTO[] getAnsatList() throws DALException;
	Integer createAnsat(AnsatDTO ans) throws DALException;
	Integer updateAnsat(AnsatDTO ans) throws DALException;
	Integer deleteAnsat(AnsatDTO ans) throws DALException;
}
