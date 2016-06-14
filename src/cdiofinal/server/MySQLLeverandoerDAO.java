package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.Connector;
import cdiofinal.server.LeverandoerDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.LeverandoerDTO;

public class MySQLLeverandoerDAO implements LeverandoerDAO {

	@Override
	public LeverandoerDTO getLeverandoer(int leverandoerId) throws DALException {		
		try{
			ResultSet rs = Connector.doQuery("SELECT * FROM leverandoer WHERE leverandoer_id = " + leverandoerId + "';");
			
			if(!rs.first()) return null; 
			return new LeverandoerDTO(rs.getInt("leverandoer_id"), rs.getString("leverandoer_navn"));
		}
		catch(SQLException e) {throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<LeverandoerDTO> getLeverandoerList() throws DALException {
		ResultSet rs;
		try {
			rs = Connector.doQuery("SELECT * FROM leverandoer;");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return populateList(rs);
	}
	public List<LeverandoerDTO> populateList(ResultSet rs) throws DALException
	{
		try{
			if(!rs.first()) 
				throw new DALException("Ingen leverandoerer blev fundet");
			List<LeverandoerDTO> leverandoer = new ArrayList<LeverandoerDTO>();
			do
			{
				LeverandoerDTO lev = new LeverandoerDTO(rs.getInt("leverandoer_id"), rs.getString("leverandoer_navn"));
				leverandoer.add(lev);
			}while(rs.next());
			return leverandoer;
		}
		catch(SQLException e) 
		{
			throw new DALException(e);
		}
	}
	

	@Override
	public int createLeverandoer(LeverandoerDTO leverandoer) throws DALException {
		try {
			return Connector.doUpdate("INSERT INTO leverandoer(leverandoer_id, leverandoer_navn) VALUES " + 
					"(" + leverandoer.getLeverandoerId() + ", '" + leverandoer.getLeverandoerNavn() + "');");
			
		} catch (SQLException e) {
			if(SQLStates.isDuplicateFailure(e.getSQLState()))
			{
				throw new DALException("En leverandoer med dette ID eksisterer allerede");
			}
			else throw new DALException(e.getMessage());
		}
	}

	@Override
	public int updateLeverandoer(LeverandoerDTO leverandoer) throws DALException{
			try {
				return Connector.doUpdate("UPDATE leverandoer SET leverandoer_navn = '" + 
						leverandoer.getLeverandoerNavn() + "' WHERE leverandoer_id = " + leverandoer.getLeverandoerId() + ";");
			} catch (SQLException e) {
				throw new DALException("");
			}
	}
}
