package cdiofinal.client;
 
import cdiofinal.shared.DALException;
import cdiofinal.shared.LeverandoerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("leverandoer")
public interface LeverandoerRPCInterface extends RemoteService{
	LeverandoerDTO getLeverandoer(int rvId) throws DALException;
	LeverandoerDTO[] getLeverandoerList() throws DALException;
 	Integer createLeverandoer(LeverandoerDTO lev) throws DALException;
	Integer updateLeverandoer(LeverandoerDTO lev) throws DALException;
	

}