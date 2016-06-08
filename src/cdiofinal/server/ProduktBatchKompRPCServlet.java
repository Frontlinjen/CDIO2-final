package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.ProduktBatchKompRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLProduktBatchKompDAO;
import cdiofinal.shared.ProduktBatchKompDTO;

public class ProduktBatchKompRPCServlet extends RemoteServiceServlet implements ProduktBatchKompRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktBatchKompDAO database = new MySQLProduktBatchKompDAO();
	
	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid) {
		try {
			return database.getProduktBatchKomp(pbid, rabaid);
		} catch (DALException e) {
			System.out.println("Failed at getProduktBatchKomp");
		}
		return null;
	}

	@Override
	public ProduktBatchKompDTO[] getProduktBatchKompList() {
					
					try {
						List<ProduktBatchKompDTO> produktbatchkomponenter = database.getProduktBatchKompList();
						ProduktBatchKompDTO[] produktbatchkomponenterArray = new ProduktBatchKompDTO[produktbatchkomponenter.size()];
						return produktbatchkomponenter.toArray(produktbatchkomponenterArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createProduktBatchKomp(ProduktBatchKompDTO pbk) {
		try {
		return database.createProduktBatchKomp(pbk);
		} catch (DALException e){
			System.out.println("Failed at createProduktBatchKomp");
		}
		return 0;
	}

	@Override
	public Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk) {
		try {
			return database.updateProduktBatchKomp(pbk);
			} catch (DALException e){
				System.out.println("Failed at updateProduktBatchKomp");
			}
			return 0;
		
	}
		
	}
	public static void main(String[] args) {
		ProduktBatchKompRPCServlet servlet = new ProduktBatchKompRPCServlet();
		for (ProduktBatchKompDTO string : servlet.getProduktBatchKompList()) {
			System.out.println(string);
		}
		
    }
	
}