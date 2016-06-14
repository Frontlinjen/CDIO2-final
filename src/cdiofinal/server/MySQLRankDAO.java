package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.shared.RankDTO;

public class MySQLRankDAO implements RankDAO{
	@Override
	public RankDTO getRank(int titel) throws DALException {
		try {
		ResultSet rs = Connector.doQuery("SELECT * FROM rank WHERE titel = " + titel + ";");
		
			if(!rs.first()) return null;
	    	return new RankDTO (rs.getInt("titel"), rs.getString("rank"));
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage()); }
	}

	@Override
	public List<RankDTO> getRankList() throws DALException {
		List<RankDTO> list = new ArrayList<RankDTO>();
		try
		{
		ResultSet rs = Connector.doQuery("SELECT * FROM rank;");
		
			while (rs.next()) 
			{
				list.add(new RankDTO(rs.getInt("titel"), rs.getString("rank")));
			}
		}
		catch (SQLException e) { throw new DALException(e.getMessage()); }
		return list;
	}

}
