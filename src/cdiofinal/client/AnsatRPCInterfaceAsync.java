package cdiofinal.client;

import cdiofinal.shared.AnsatDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface AnsatRPCInterfaceAsync {
	public void getAnsat(String cpr, AsyncCallback<AnsatDTO> callback);
	public void  getAnsatList(AsyncCallback<AnsatDTO[]> callback);
	public void createAnsat(AnsatDTO user, AsyncCallback<Integer> callback);
	public void updateAnsat(AnsatDTO user, AsyncCallback<Integer> callback);
	public void deleteAnsat(AnsatDTO user, AsyncCallback<Integer> callback);

}
