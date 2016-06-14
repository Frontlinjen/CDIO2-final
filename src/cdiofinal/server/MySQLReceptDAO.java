package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cdiofinal.server.Connector;
import cdiofinal.server.ReceptDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO {

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		try {
		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId + ";");
		
			if(!rs.first()) throw new DALException("Recepten med receptId " + receptId + " findes ikke");
	    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage()); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		try
		{
		ResultSet rs = Connector.doQuery("SELECT * FROM recept;");
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e.getMessage()); }
		return list;
	}

	@Override
	public int createRecept(ReceptDTO recept) throws DALException {
		try
		{
			return Connector.doUpdate(
					"INSERT INTO recept(recept_id, recept_navn) VALUES " +
					"(" + recept.getReceptId() + ", '" + recept.getReceptNavn() + "');"
				);
		}
		catch(SQLException e)
		{
			if(SQLStates.isDuplicateFailure(e.getErrorCode()))
			{
				throw new DALException("Recepten eksisterer allerede!");
			}
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public int updateRecept(ReceptDTO recept) throws DALException {
		try
		{
		return Connector.doUpdate(
				"UPDATE recept SET recept_navn = '" + recept.getReceptNavn() + "' WHERE recept_id = " + recept.getReceptId() + ";");
		}
		catch(SQLException e)
		{
			throw new DALException(e.getMessage());
		}
	}

}
