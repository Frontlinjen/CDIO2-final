package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.Connector;
import cdiofinal.server.ProduktBatchKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchKompDTO;
import cdiofinal.shared.RaavareBatchDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int raavarebatchId) throws DALException {
		 try {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND raavarebatch_id = " + raavarebatchId + ";");
	   
	    	if (!rs.first())
	    		return null;
	    	return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("raavarebatch_id"), rs.getDouble("tara"), 
	    			   rs.getDouble("netto"), rs.getString("cpr"));
	    }
	    catch (SQLException e) {
	    	throw new DALException(e.getMessage()); 
	    	}
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		try
		{
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent;");
		
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("raavarebatch_id"), 
						 rs.getDouble("tara"), rs.getDouble("netto"), rs.getString("cpr")));
			}
		}
		catch (SQLException e) { 
			throw new DALException(e.getMessage()); 
			}
		return list;
	}

	@Override
	public int createProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		try
		{
		return Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, raavarebatch_id, tara, netto, cpr) VALUES ("
				+ pbkomp.getPbId() + ", " + pbkomp.getRaavarebatchId() + ", '" + pbkomp.getTara() + "', '" 
				+ pbkomp.getNetto()	+ "', '" + pbkomp.getCpr() + "');"
			);
		}
		catch(SQLException e)
		{
			if(SQLStates.isDuplicateFailure(e.getSQLState()))
			{
				throw new DALException("Produktbatchkomponenten eksisterer allerede!");
			}
			else if(SQLStates.isIntegrityFailure(e.getSQLState()))
					{
						throw new DALException(getIntegrityError(pbkomp));
					}
			//Cant handle this error. Pass it on to the user. 
			throw new DALException(e.getMessage());
		}
	}
	
	
	private String getIntegrityError(ProduktBatchKompDTO pbkomp) throws DALException
	{
		RaavareBatchDAO raavareBatches = new MySQLRaavareBatchDAO();
		ProduktBatchDAO productBatches = new MySQLProduktbatchDAO();
		if(raavareBatches.getRaavareBatch(pbkomp.getRaavarebatchId())==null)
		{
			return "Raavarebatchen eksisterer ikke";
		}
		else if(productBatches.getProduktBatch(pbkomp.getPbId())==null)
		{
			return "ProduktBatchen eksisterer ikke";
		}
		return "Unknown integrity error";
	}
	@Override
	public int updateProduktBatchKomp(ProduktBatchKompDTO pbkomp) throws DALException {
		try
		{
		return Connector.doUpdate(
				"UPDATE produktbatchkomponent SET tara = '" + pbkomp.getTara() + "', netto = '" + pbkomp.getNetto() +
				"', cpr = '" + pbkomp.getCpr() + "' WHERE pb_id = " + pbkomp.getPbId() + " AND raavarebatch_id = " 
						+ pbkomp.getRaavarebatchId() + ";"
				);	
		}
		catch(SQLException e)
		{
			if(SQLStates.isIntegrityFailure(e.getSQLState()))
					{
						throw new DALException(getIntegrityError(pbkomp));
					}
			//Cant handle this error. Pass it on to the user. 
			throw new DALException(e.getMessage());
		}
	}

}
