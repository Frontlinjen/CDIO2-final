package cdiofinal.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdiofinal.shared.RankDTO;
import cdiofinal.shared.ReceptDTO;

public class MySQLRankDAO implements RankDAO{

	@Override
	public RankDTO getRank(int titel) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM rank WHERE titel = " + titel + ";");
		try {
			if(!rs.first()) throw new DALException("Ranken med titel " + titel + " findes ikke");
	    	return new RankDTO (rs.getInt("titel"), rs.getString("rank"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RankDTO> getRankList() throws DALException {
		List<RankDTO> list = new ArrayList<RankDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM rank;");
		try
		{
			while (rs.next()) 
			{
				list.add(new RankDTO(rs.getInt("titel"), rs.getString("rank")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public int createRank(RankDTO rank) throws DALException {
		return Connector.doUpdate(
				"INSERT INTO rank(titel, rank) VALUES " +
				"(" + rank.getTitel() + ", '" + rank.getRank() + "');"
			);
	}

	@Override
	public int updateRank(RankDTO rank) throws DALException {
		return Connector.doUpdate(
				"UPDATE rank SET rank = '" + rank.getRank() + "' WHERE titel = " + rank.getTitel()
				+ ";");
	}

}
