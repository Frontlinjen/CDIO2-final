package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.Connector;
import cdiofinal.server.ProduktBatchDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;

public class MySQLProduktbatchDAO implements ProduktBatchDAO {

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
	    try {
			ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId + ";");

	    	if (!rs.first()) return null;
	    	return new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage()); }
		
	}
	

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs;
		try
		{
			rs = Connector.doQuery("SELECT * FROM produktbatch;");
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public int createProduktBatch(ProduktBatchDTO ans) throws DALException {
		try {
			return Connector.doUpdate(
					"INSERT INTO produktbatch(pb_id, status, recept_id) VALUES " +
					"(" + ans.getPbId() + ", " + ans.getStatus() + ", " + ans.getReceptId() + ");"
				);
		} catch (SQLException e) {
			if(SQLStates.isDuplicateFailure(e.getSQLState()))
			{
				throw new DALException("Productbatch eksisterer allerede.");
			}
			else if(SQLStates.isIntegrityFailure(e.getSQLState()))
			{
				throw new DALException("Recept id'en eksisterer allerede.");
			}
			else throw new DALException(e.getMessage());
		}
		
		
	}

	@Override
	public int updateProduktBatch(ProduktBatchDTO ans) throws DALException {
		try {
			return Connector.doUpdate(
					"UPDATE produktbatch SET  pb_id = " + ans.getPbId() + ", status =  " + ans.getStatus() + 
					", recept_id = " + ans.getReceptId() + " WHERE pb_id = " + ans.getPbId() + ";"
					);
			
		} catch (SQLException e) {
			if(SQLStates.isIntegrityFailure(e.getSQLState()))
			{
				throw new DALException("Recept id'en eksisterer allerede.");
			}
			else throw new DALException(e.getMessage());
		}		
	}

}
