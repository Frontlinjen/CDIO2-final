package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ansatte")
public interface AnsatRPCInterface extends RemoteService{
	AnsatDTO getAnsat(String cpr);
	AnsatDTO[] getAnsatList();
	Integer createAnsat(AnsatDTO ans);
	Integer updateAnsat(AnsatDTO ans);
	Integer deleteAnsat(AnsatDTO ans);
}
