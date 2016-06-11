package cdiofinal.client;
 
import cdiofinal.shared.DALException;
import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("leverandoer")
public interface LeverandoerRPCInterface extends RemoteService{
	LeverandoerDTO getLeverandoer(int rvId, String token) throws DALException;
	LeverandoerDTO[] getLeverandoerList(String token) throws DALException;
 	LeverandoerDTO createLeverandoer(LeverandoerDTO lev, String token) throws DALException;
	Integer updateLeverandoer(LeverandoerDTO lev, String token) throws DALException;
	

}