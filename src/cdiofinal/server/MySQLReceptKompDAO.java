package cdiofinal.server;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cdiofinal.server.Connector;
import cdiofinal.server.DALException;
import cdiofinal.server.ReceptKompDAO;
import cdiofinal.shared.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO{

	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId + " AND raavare_id = " + raavareId + ";");
		try{
			if (!rs.first()) throw new DALException("Receptkomponenten med receptId " + receptId + " og raavareId " + raavareId + " findes ikke");
			return new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId +";");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent;");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
	
	public int createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		return Connector.doUpdate("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES (" + receptkomponent.getReceptId() + ","
		+ receptkomponent.getRaavareId() + "," + receptkomponent.getNomNetto() + "," + receptkomponent.getTolerance() + ");");
	}

	public int updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		return Connector.doUpdate("UPDATE receptkomponent SET nom_netto = '" + receptkomponent.getNomNetto() + "', tolerance = '"
		+ receptkomponent.getTolerance() + "' WHERE recept_id = " + receptkomponent.getReceptId() + " AND raavare_id = " + receptkomponent.getRaavareId() + ";");
		
	}

}
