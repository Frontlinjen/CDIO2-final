package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.RaavareRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLRaavareDAO;
import cdiofinal.shared.RaavareDTO;

public class RaavareRPCServlet extends RemoteServiceServlet implements RaavareRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLRaavareDAO database = new MySQLRaavareDAO();
	
	
	@Override
	public RaavareDTO getRaavare(int raavare_id) {
		try {
			return database.getRaavare(raavare_id);
		} catch (DALException e) {
			System.out.println("Failed at get Raavare");
		}
		return null;
	}

	@Override
	public RaavareDTO[] getRaavareList() {
					
					try {
						List<RaavareDTO> raavare = database.getRaavareList();
						RaavareDTO[] raavareArray = new RaavareDTO[raavare.size()];
						return raavare.toArray(raavareArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createRaavare(RaavareDTO ans) {
		try {
		return database.createRaavare(ans);
		} catch (DALException e){
			System.out.println("Failed at create Raavare");
		}
		return 0;
	}

	@Override
	public Integer updateRaavare(RaavareDTO ans) {
		try {
			return database.updateRaavare(ans);
			} catch (DALException e){
				System.out.println("Failed at update Raavare");
			}
			return 0;
		
	}

	public static void main(String[] args) {
		RaavareRPCServlet servlet = new RaavareRPCServlet();
		for (RaavareDTO string : servlet.getRaavareList()) {
			System.out.println(string);
		}
		
    }
	
}