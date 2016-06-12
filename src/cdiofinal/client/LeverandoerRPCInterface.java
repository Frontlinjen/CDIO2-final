package cdiofinal.client;
 
import cdiofinal.shared.DALException;
import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("leverandoer")
public interface LeverandoerRPCInterface extends RemoteService{
	LeverandoerDTO getLeverandoer(int rvId, String token) throws Exception;
	LeverandoerDTO[] getLeverandoerList(String token) throws Exception;
 	LeverandoerDTO createLeverandoer(LeverandoerDTO lev, String token) throws Exception;
	Integer updateLeverandoer(LeverandoerDTO lev, String token) throws Exception;
	

}