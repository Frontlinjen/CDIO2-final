package cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdiofinal.shared.RankDTO;

public class RankRPCServlet extends RemoteServiceServlet implements RankDAO{

	private static final long serialVersionUID = 1L;
	
	MySQLRankDAO dao = new MySQLRankDAO();

	@Override
	public RankDTO getRank(int titel) throws DALException {
		try{
			return dao.getRank(titel);
		}
		catch(DALException e){
			System.out.println("Failed at getRank");
		}
		return null;
	}

	@Override
	public List<RankDTO> getRankList() throws DALException {
		try {
			List<RankDTO> rank = dao.getRankList();
			return rank;
		} catch (DALException e) {
			return null;
		}	
	}
	

}
