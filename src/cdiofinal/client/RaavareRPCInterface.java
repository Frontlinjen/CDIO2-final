package cdiofinal.client;

import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Raavare")
public interface RaavareRPCInterface extends RemoteService{
	RaavareDTO getRaavare(int raavareId);
	RaavareDTO[] getRaavareList();
	Integer createRaavare(RaavareDTO raa);
	Integer updateRaavare(RaavareDTO raa);
	Integer deleteRaavare(RaavareDTO raa);
	

}
