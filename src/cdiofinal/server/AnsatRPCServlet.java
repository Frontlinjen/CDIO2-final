package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.AnsatRPCInterface;
import cdiofinal.server.DALException;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;

public class AnsatRPCServlet extends RemoteServiceServlet implements AnsatRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLAnsatDAO database = new MySQLAnsatDAO();
	
	
	@Override
	public AnsatDTO getAnsat(String cpr) {
		try {
			return database.getAnsat(cpr);
		} catch (DALException e) {
			System.out.println("Failed at getAnsat");
		}
		return null;
	}

	@Override
	public AnsatDTO[] getAnsatList() {
					try {
						List<AnsatDTO> ansatte = database.getAnsatList();
						AnsatDTO[] ansatteArray = new AnsatDTO[ansatte.size()];
						return ansatte.toArray(ansatteArray);
					} catch (DALException e) {
						return null;
					}			
	}

	@Override
	public Integer createAnsat(AnsatDTO ans) {
		try {
		return database.createAnsat(ans);
		} catch (DALException e){
			System.out.println("Failed at createAnsat");
		}
		return 0;
	}

	@Override
	public Integer updateAnsat(AnsatDTO ans) {
		try {
			return database.updateAnsat(ans);
			} catch (DALException e){
				System.out.println("Failed at updateAnsat");
			}
			return 0;
		
	}

	@Override
	public Integer deleteAnsat(AnsatDTO ans) {
		try {
			return database.deleteAnsat(ans);
			} catch (DALException e){
				System.out.println("Failed at deleteAnsat");
			}
			return 0;
		
	}
	
}
