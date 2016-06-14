package cdiofinal.server;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cdiofinal.server.RaavareDAO;
import cdiofinal.shared.DALException;
import cdiofinal.shared.RaavareDTO;

public class MySQLRaavareDAO implements RaavareDAO{

//	
//	public MySQLRaavareDAO()
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
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		ResultSet rs;
		try{
			rs = Connector.doQuery("SELECT * FROM raavare WHERE raavare_id = " + raavareId + ";");
			if(!rs.first())
				return null;
			return new RaavareDTO (rs.getInt("raavare_id"), rs.getString("raavare_navn"));
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
			}
			
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM raavare;");
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn")));
			}
		}
		catch (SQLException e) { 
			throw new DALException(e.getMessage()); 
		}
		return list;
	}

	@Override
	public int createRaavare(RaavareDTO raavare) throws DALException {
		try{
			return Connector.doUpdate("INSERT INTO raavare(raavare_id, raavare_navn) VALUES " +
					"(" + raavare.getRaavareId() + ", '" + raavare.getRaavareNavn() + "');");
		}catch(SQLException e){
			
			if(SQLStates.isDuplicateFailure(e.getSQLState()))
			{
				throw new DALException("raavare eksisterer allerede!");
				//Figure out what constraint failed
			}
			throw new DALException(e.getMessage());
		}
		
		
	}

	@Override
	public int updateRaavare(RaavareDTO raavare) throws DALException {
		try{
			return Connector.doUpdate("UPDATE raavare SET raavare_navn = '" 
					+ raavare.getRaavareNavn() + "' WHERE raavare_id = " + raavare.getRaavareId() + ";");
		}catch(SQLException e){
			throw new DALException(e.getMessage());
		}
	}

}
