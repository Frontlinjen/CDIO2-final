package cdiofinal.server;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.client.ProduktBatchKompRPCInterface;
import cdiofinal.server.MySQLProduktBatchKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.ProduktBatchKompDTO;

public class ProduktBatchKompRPCServlet extends RemoteServiceServlet implements ProduktBatchKompRPCInterface {

	private static final long serialVersionUID = 1L;
	MySQLProduktBatchKompDAO database = new MySQLProduktBatchKompDAO();
	
	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbid, int rabaid) throws DALException {
		try {
			return database.getProduktBatchKomp(pbid, rabaid);
		} catch (DALException e) {
			throw new DALException("An error occoured when getting a produktbatchkomp. Please contact your sysadmin.");
		}
	}

	@Override
	public ProduktBatchKompDTO[] getProduktBatchKompList() throws DALException{
					
					try {
						List<ProduktBatchKompDTO> produktbatchkomponenter = database.getProduktBatchKompList();
						ProduktBatchKompDTO[] produktbatchkomponenterArray = new ProduktBatchKompDTO[produktbatchkomponenter.size()];
						return produktbatchkomponenter.toArray(produktbatchkomponenterArray);
					} catch (DALException e) {
						throw new DALException("An error occoured when getting produktbatchkomp list. Please contact your sysadmin.");
					}			
	}

	@Override
	public Integer createProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException{
		if(FieldVerifier.isValidId(pbk.getPbId())==true || FieldVerifier.isValidId(pbk.getRaavarebatchId())==true || FieldVerifier.isValidCpr(Integer.parseInt(pbk.getCpr())))
		try {
		return database.createProduktBatchKomp(pbk);
		} catch (DALException e){
			throw new DALException("An error occoured when creating a produktbatchkomp. Please contact your sysadmin.");
		}
		return 0;
	}

	@Override
	public Integer updateProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException{
		if(FieldVerifier.isValidId(pbk.getPbId())==true || FieldVerifier.isValidId(pbk.getRaavarebatchId())==true || FieldVerifier.isValidCpr(Integer.parseInt(pbk.getCpr())))
		try {
			return database.updateProduktBatchKomp(pbk);
			} catch (DALException e){
				throw new DALException("An error occoured when uppdating a produktbatchkomp. Please contact your sysadmin.");
			}
			return 0;
		
	}
		
}