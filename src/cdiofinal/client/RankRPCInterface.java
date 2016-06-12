package cdiofinal.client;

import cdiofinal.shared.RankDTO;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rank")
public interface RankRPCInterface {
	RankDTO getRank(int titel) throws Exception;
	RankDTO[] getRankList() throws Exception;
	Integer createRank(RankDTO rank) throws Exception;
	Integer updateRank(RankDTO rank) throws Exception;
	Integer deleteRank(RankDTO rank) throws Exception;
}
