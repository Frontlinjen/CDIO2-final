package cdiofinal.server;
import java.util.List;

import cdiofinal.client.ProduktBatchKompRPCInterface;
import cdiofinal.server.MySQLProduktBatchKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ProduktBatchKompDTO;

public class ProduktBatchKompRPCServlet extends ValidationServlet implements ProduktBatchKompRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktBatchKompDAO database = new MySQLProduktBatchKompDAO();


	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid, String token) throws Exception {
		try {
			if(isValid(token, 0))
				return database.getProduktBatchKomp(pbid, rabaid);
		} catch (DALException e) {
			throw new DALException(gettingError("product batch component"));
		}
		return null;
	}

	@Override
	public ProduktBatchKompDTO[] getProduktBatchKompList(String token) throws Exception{

		try {
			if(isValid(token, 0)){
				List<ProduktBatchKompDTO> produktbatchkomponenter = database.getProduktBatchKompList();
				ProduktBatchKompDTO[] produktbatchkomponenterArray = new ProduktBatchKompDTO[produktbatchkomponenter.size()];
				return produktbatchkomponenter.toArray(produktbatchkomponenterArray);
			}
		} catch (DALException e) {
			throw new DALException(gettingListError("product batch component"));
		}
		return null;			
	}

	@Override
	public ProduktBatchKompDTO createProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception{
		if(FieldVerifier.isValidId(pbk.getPbId())==true || FieldVerifier.isValidId(pbk.getRaavarebatchId())==true || FieldVerifier.isValidCpr(Integer.parseInt(pbk.getCpr())))
			try {
				if(isValid(token, 0)){
					if(database.createProduktBatchKomp(pbk)!=0){
						return pbk;
					}
				}
			} catch (DALException e){
				throw new DALException(creatingError("product batch component"));
			}
		return null;
	}

	@Override
	public Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception{
		if(FieldVerifier.isValidId(pbk.getPbId())==true || FieldVerifier.isValidId(pbk.getRaavarebatchId())==true || FieldVerifier.isValidCpr(Integer.parseInt(pbk.getCpr())))
			try {
				if(isValid(token, 0))
					return database.updateProduktBatchKomp(pbk);
			} catch (DALException e){
				throw new DALException(updatingError("product batch component"));
			}
		return 0;

	}

}