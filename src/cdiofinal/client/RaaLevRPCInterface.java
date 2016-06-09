package cdiofinal.client;

import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.RaavareDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Raavare og Leverandoer")
public interface RaaLevRPCInterface extends RemoteService{
	RaavareDTO getRaavare(int raavareId);
	RaavareDTO[] getRaavareList();
	Integer createRaavare(RaavareDTO raa);
	Integer updateRaavare(RaavareDTO raa);
	Integer deleteRaavare(RaavareDTO raa);
	LeverandoerDTO getLeverandoer(int rvId);
	LeverandoerDTO[] getLeverandoerList();
	Integer createLeverandoer(LeverandoerDTO lev);
	Integer updateLeverandoer(LeverandoerDTO lev);
	Integer deleteLeverandoer(LeverandoerDTO lev);

}
