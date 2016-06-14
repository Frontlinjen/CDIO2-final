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
		if(FieldVerifier.isValidId(pbk.getPbId())==true 
		&& FieldVerifier.isValidId(pbk.getRaavarebatchId())==true 
		&& FieldVerifier.isValidCpr(pbk.getCpr()))
		{
				if(isValid(token, 0)){
					database.createProduktBatchKomp(pbk);
					}
		}
		else
		{
			Window.alert("Kunne ikke oprette ny ProduktBatchKomponent, tjek oplysningerne igen.");
		}
		return null;
	}

	@Override
	public Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk, String token) throws Exception{
		if(FieldVerifier.isValidId(pbk.getPbId())==true
		&& FieldVerifier.isValidId(pbk.getRaavarebatchId())==true 
		&& FieldVerifier.isValidCpr(pbk.getCpr()))
		{
				if(isValid(token, 0)){
					return database.updateProduktBatchKomp(pbk);
				}
		}
		else
		{
			Window.alert("Kunne ikke opdatere produktBatchKomponenten, tjek oplysningerne igen");
		}
		return null;
		}
}