package cdiofinal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import cdiofinal.shared.RankDTO;

public interface RankRPCInterfaceAsync {
	public void getRank(int titel, AsyncCallback<RankDTO> callback);
	public void getRankList(AsyncCallback<RankDTO[]> callback);
	public void createRank(RankDTO rank, AsyncCallback<Integer> callback);
	public void updateRank(RankDTO rank, AsyncCallback<Integer> callback);
	public void deleteRank(RankDTO rank, AsyncCallback<Integer> callback);
}
