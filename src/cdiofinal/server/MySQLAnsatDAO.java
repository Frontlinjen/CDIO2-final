package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

import java.util.ArrayList;

public class MySQLAnsatDAO implements AnsatDAO {
//	public MySQLAnsatDAO()
//	{
//		//Connects to database: 
//		try {
//			new Connector();
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public AnsatDTO getAnsat(String cpr) throws DALException {
	    try {
	    	ResultSet rs = Connector.doQuery("SELECT * FROM ansat WHERE cpr = " + cpr + ";");
	    	
	    	if (!rs.first()) return null;
	    	return new AnsatDTO (rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("password"), rs.getInt("titel"));
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage()); }
		
	}
	
	@Override
	public int createAnsat(AnsatDTO ans) throws DALException {		
		try {
			return Connector.doUpdate("INSERT INTO ansat(cpr, opr_navn, ini, password, titel) VALUES " +
					"(" + ans.getCpr() + ", '" + ans.getOprNavn() + "', '" + ans.getIni() + "', '" + 
					ans.getPassword() + "', '" + ans.getTitel() + "');");
		} catch (SQLException e) {
			if (SQLStates.isDuplicateFailure(e.getErrorCode())) {
				throw new DALException("En ansat med dette CPR nummer eksisterer allerede");
			}
			else throw new DALException(e.getMessage());
		}	
	
	}
	
	@Override
	public int updateAnsat(AnsatDTO ans) throws DALException {
		try {
			return Connector.doUpdate(
					"UPDATE ansat SET  opr_navn = '" + ans.getOprNavn() + "', ini =  '" + ans.getIni() + 
					"', password = '" + ans.getPassword() + "', titel = " + ans.getTitel() + " WHERE cpr = '" +
					ans.getCpr() + "';"
			);
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
	
	@Override
	public List<AnsatDTO> getAnsatList() throws DALException {
		List<AnsatDTO> list = new ArrayList<AnsatDTO>();
		ResultSet rs;
		try
		{
			rs = Connector.doQuery("SELECT * FROM ansat;");
			while (rs.next()) 
			{
				list.add(new AnsatDTO(rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("password"), rs.getInt("titel")));
			}
		}
		catch (SQLException e) { throw new DALException(e.getMessage()); }
		return list;
	}

	@Override
	public int deleteAnsat(AnsatDTO ans) throws DALException {
		try {
			return Connector.doUpdate("DELETE FROM ansat WHERE cpr = " + ans.getCpr() + ";");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

}
	
