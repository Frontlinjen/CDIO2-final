package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface AnsatRPCInterfaceAsync {
	public void getAnsat(String cpr, String token, AsyncCallback<AnsatDTO> callback);
	public void  getAnsatList(String token, AsyncCallback<AnsatDTO[]> callback);
	public void createAnsat(AnsatDTO user, String token, AsyncCallback<AnsatDTO> callback);
	public void updateAnsat(AnsatDTO user, String token, AsyncCallback<Integer> callback);
	public void deleteAnsat(AnsatDTO user, String token, AsyncCallback<Integer> callback);

}
