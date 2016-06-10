package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.Connector;
import cdiofinal.server.ProduktBatchKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int raavarebatchId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND raavarebatch_id = " + raavarebatchId + ";");
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatchkomponenten med pb_id'et " + pbId + " og raavarebatch_id " + raavarebatchId + " findes ikke");
	    	return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("raavarebatch_id"), rs.getDouble("tara"), 
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
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("raavarebatch_id"), 
						 rs.getDouble("tara"), rs.getDouble("netto"), rs.getString("cpr")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public int createProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		return Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, raavarebatch_id, tara, netto, cpr) VALUES ("
				+ pbkomp.getPbId() + ", " + pbkomp.getRaavarebatchId() + ", '" + pbkomp.getTara() + "', '" 
				+ pbkomp.getNetto()	+ "', '" + pbkomp.getCpr() + "');"
			);
	}

	@Override
	public int updateProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		return Connector.doUpdate(
				"UPDATE produktbatchkomponent SET tara = '" + pbkomp.getTara() + "', netto = '" + pbkomp.getNetto() +
				"', cpr = '" + pbkomp.getCpr() + "' WHERE pb_id = " + pbkomp.getPbId() + " AND raavarebatch_id = " 
						+ pbkomp.getRaavarebatchId() + ";"
				);		
	}

}
