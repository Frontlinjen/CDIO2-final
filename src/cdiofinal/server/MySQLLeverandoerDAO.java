package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.Connector;
import cdiofinal.server.DALException;
import cdiofinal.server.LeverandoerDAO;
import cdiofinal.shared.LeverandoerDTO;

public class MySQLLeverandoerDAO implements LeverandoerDAO {

	@Override
	public LeverandoerDTO getLeverandoer(int leverandoerId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM leverandoer WHERE leverandoer_id = " + leverandoerId + "';");
		
		try{
			if(!rs.first()) 
				throw new DALException("Leverandoeren med leverandoer_id " + leverandoerId + " findes ikke.");
			return new LeverandoerDTO(rs.getInt("leverandoer_id"), rs.getString("leverandoer_navn"));
		}
		catch(SQLException e) 
		{
			throw new DALException(e);
		}
	}

	@Override
	public List<LeverandoerDTO> getLeverandoerList() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM leverandoer;");
		return populateList(rs);
	}
	public List<LeverandoerDTO> populateList(ResultSet rs) throws DALException
	{
		try{
			if(!rs.first()) 
				throw new DALException("Ingen raavarebatches blev fundet");
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
	public void createLeverandoer(LeverandoerDTO leverandoer) throws DALException {
		Connector.doUpdate(String.format("INSERT INTO leverandoer(raavare_id, leverandoer_navn, maengde) VALUES(" + leverandoer.getRaavareId() + ", '" +
		leverandoer.getLeverandoerNavn() + "', " + leverandoer.getMaengde() + ");"));
//		if(updateCount==0)
//			throw new DALException("Failed to add new raavarebatch!");
	}

	@Override
	public void updateLeverandoer(LeverandoerDTO leverandoer) throws DALException {
		Connector.doUpdate(String.format("UPDATE leverandoer SET maengde = " + leverandoer.getMaengde() +
		"WHERE raavare_id = " + leverandoer.getRaavareId() + " AND leverandoer_navn = '" + leverandoer.getLeverandoerNavn() + "';"));
	}

	
}
