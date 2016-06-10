package cdiofinal.client;

import cdiofinal.shared.RankDTO;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rank")
public interface RankRPCInterface {
	RankDTO getRank(int titel);
	RankDTO[] getRankList();
	Integer createRank(RankDTO rank);
	Integer updateRank(RankDTO rank);
	Integer deleteRank(RankDTO rank);
}
