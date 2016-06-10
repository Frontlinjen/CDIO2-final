package cdiofinal.client;
 
import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("leverandoer")
public interface LeverandoerRPCInterface extends RemoteService{
	LeverandoerDTO getLeverandoer(int rvId);
	LeverandoerDTO[] getLeverandoerList();
 	Integer createLeverandoer(LeverandoerDTO lev);
	Integer updateLeverandoer(LeverandoerDTO lev);
	

}