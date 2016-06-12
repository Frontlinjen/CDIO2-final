package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ansatte")
public interface AnsatRPCInterface extends RemoteService{
	AnsatDTO getAnsat(String cpr, String token) throws Exception;
	AnsatDTO[] getAnsatList(String token) throws Exception;
	AnsatDTO createAnsat(AnsatDTO ans, String token) throws Exception;
	Integer updateAnsat(AnsatDTO ans, String token) throws Exception;
	Integer deleteAnsat(AnsatDTO ans, String token) throws Exception;
}
