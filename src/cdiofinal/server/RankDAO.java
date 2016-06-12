package cdiofinal.server;

import java.util.List;

import cdiofinal.shared.DALException;
import cdiofinal.shared.RankDTO;

public interface RankDAO {
	RankDTO getRank(int titel) throws Exception;
	List<RankDTO> getRankList() throws Exception;
}
