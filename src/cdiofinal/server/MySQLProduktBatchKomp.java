package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKomp implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND raavare_id = " + rbId + ";");
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatchkomponenten med pb_id'et " + pbId + " og raavare_id " + rbId + " findes ikke");
	    	return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("raavare_Id"), rs.getDouble("tara"), 
	    			   rs.getDouble("netto"), rs.getString("cpr"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent;");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("raavare_Id"), 
						 rs.getDouble("tara"), rs.getDouble("netto"), rs.getString("cpr")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, raavare_Id, tara, netto, cpr) VALUES ("
				+ pbkomp.getPbId() + ", " + pbkomp.getRaavareId() + ", '" + pbkomp.getTara() + "', '" 
				+ pbkomp.getNetto()	+ "', '" + pbkomp.getCpr() + "');"
			);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchkomponent SET tara = '" + pbkomp.getTara() + "', netto = '" + pbkomp.getNetto() +
				"', cpr = '" + pbkomp.getCpr() + "' WHERE pb_id = " + pbkomp.getPbId() + " AND raavare_Id = " 
						+ pbkomp.getRaavareId() + ";"
				);		
	}

}
