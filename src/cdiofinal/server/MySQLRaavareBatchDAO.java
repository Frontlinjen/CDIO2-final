package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.server.SQLStates;
import cdiofinal.shared.DALException;
import cdiofinal.shared.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO{

	@Override
	public RaavareBatchDTO getRaavareBatch(int raavarebatchId) throws DALException {
		ResultSet rs;
		try {
			rs = Connector.doQuery("SELECT * FROM raavarebatch WHERE raavarebatch_id = " + raavarebatchId + ";");
			if (!rs.first()) 
				return null;

			return new RaavareBatchDTO(rs.getInt("raavarebatch_id"), rs.getInt("raavare_id"), rs.getInt("leverandoer_id"), rs.getDouble("maengde"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<RaavareBatchDTO> getRaavarebatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();

		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch;");
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("raavarebatch_id"), rs.getInt("raavare_id"), rs.getInt("leverandoer_id"), rs.getDouble("maengde")));
			}
		}
		catch (SQLException e) { 
			throw new DALException(e.getMessage()); 
		}
		return list;
	}

	@Override
	public int createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try{
			return Connector.doUpdate("INSERT INTO raavarebatch(raavarebatch_id, raavare_id, leverandoer_id, maengde) VALUES (" + raavarebatch.getRaavarebatchId() + ","
					+ raavarebatch.getRaavareId() + "," + raavarebatch.getLeverandoerId() + "," + raavarebatch.getMaengde() + ");");
		}catch(SQLException e){

			if(SQLStates.isDuplicateFailure(e.getErrorCode()))
			{
				throw new DALException("raavarebatch eksisterer allerede!");
				//Figure out what constraint failed
			}
			if(SQLStates.isIntegrityFailure(e.getErrorCode()))
			{
				throw new DALException(getIntegrityError(raavarebatch));
			}
			throw new DALException(e.getMessage());
		}
		

	}

	@Override
	public int updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try{
			return Connector.doUpdate("UPDATE raavarebatch SET raavare_id = " + raavarebatch.getRaavareId() + ", leverandoer_id = "
					+ raavarebatch.getLeverandoerId() + ", maengde = " + raavarebatch.getMaengde() + " WHERE raavarebatch_id = " + raavarebatch.getRaavarebatchId() + ";");
		}catch(SQLException e)
		{
			if(SQLStates.isIntegrityFailure(e.getErrorCode()))
			{
				throw new DALException(getIntegrityError(raavarebatch));
			}
			throw new DALException(e.getMessage());
		}
	}
	
	public String getIntegrityError(RaavareBatchDTO raavarebatch) throws DALException{
		RaavareDAO raaverer = new MySQLRaavareDAO();
		if(raaverer.getRaavare(raavarebatch.getRaavareId())==null)
		{
			return "Raavare eksisterer ikke!";
		}

		LeverandoerDAO leverandoer = new MySQLLeverandoerDAO();
		if(leverandoer.getLeverandoer(raavarebatch.getLeverandoerId())==null){
			return "leverandoer eksisterer ikke!";
		}
		return "Fejl i raavarebatch";
	}
}
