package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.client.Window;

import cdiofinal.client.ProduktBatchKompRPCInterface;
import cdiofinal.server.MySQLProduktBatchKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ProduktBatchKompDTO;

public class ProduktBatchKompRPCServlet extends ValidationServlet implements ProduktBatchKompRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktBatchKompDAO database = new MySQLProduktBatchKompDAO();
	final String fail ="Kunne ikke %s , produktbatchkomponenten tjek informationerne igen.";


	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid, String token) throws Exception {
			if(isValid(token, 0)){
				return database.getProduktBatchKomp(pbid, rabaid);
			}
		return null;
	}

	@Override
	public ProduktBatchKompDTO[] getProduktBatchKompList(String token) throws Exception{
			if(isValid(token, 0)){
				List<ProduktBatchKompDTO> produktbatchkomponenter = database.getProduktBatchKompList();
				ProduktBatchKompDTO[] produktbatchkomponenterArray = new ProduktBatchKompDTO[produktbatchkomponenter.size()];
				return produktbatchkomponenter.toArray(produktbatchkomponenterArray);
			}
		return null;			
	}

	@Override
	public ProduktBatchKompDTO createProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception{
		if(pbk.isValid())
		{
				if(isValid(token, 0)){
					database.createProduktBatchKomp(pbk);
					return pbk;
					}
				}
		else
		{
			throw new DALException(String.format(fail, "oprette"));
		}
		return null;
	}

	@Override
	public Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception{
		if(pbk.isValid())
		{
				if(isValid(token, 0)){
					return database.updateProduktBatchKomp(pbk);
				}
		}
		else
		{
			throw new DALException(String.format(fail, "opdatere"));
		}
		return 0;
		}
}