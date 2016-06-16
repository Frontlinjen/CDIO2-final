package cdiofinal.server;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cdiofinal.server.Connector;
import cdiofinal.server.ReceptKompDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO{

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs;
		try{
			rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId + " AND raavare_id = " + raavareId + ";");
			if (!rs.first())
				return null;
			return new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage()); 
		}
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId +";");
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent;");
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { 
			throw new DALException(e); 
		}
		return list;
	}

	@Override
	public int createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try{
			return Connector.doUpdate("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES (" + receptkomponent.getReceptId() + ","
					+ receptkomponent.getRaavareId() + "," + receptkomponent.getNomNetto() + "," + receptkomponent.getTolerance() + ");");
		} catch (SQLException e){
			if(SQLStates.isDuplicateFailure(e.getErrorCode()))
			{
				throw new DALException("receptkomp eksisterer allerede!");
			}
			if(SQLStates.isIntegrityFailure(e.getErrorCode()))
			{
				throw new DALException(getIntergrityError(receptkomponent));
			}
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public int updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try{
			return Connector.doUpdate("UPDATE receptkomponent SET nom_netto = " + receptkomponent.getNomNetto() + ", tolerance = "
					+ receptkomponent.getTolerance() + " WHERE recept_id = " + receptkomponent.getReceptId() + " AND raavare_id = " + receptkomponent.getRaavareId() + ";");
		} catch(SQLException e) {
			if(SQLStates.isIntegrityFailure(e.getErrorCode()))
			{
				throw new DALException(getIntergrityError(receptkomponent));
			}
			throw new DALException(e.getMessage());
		}
	}
	
	public String getIntergrityError(ReceptKompDTO receptkomponent) throws DALException{
		RaavareDAO raaverer = new MySQLRaavareDAO();
		if(raaverer.getRaavare(receptkomponent.getRaavareId())==null)
		{
			return "Raavare eksisterer ikke!";
		}
		return "Fejl i raavare!";
	}

}
