package cdiofinal.shared;

import java.io.Serializable;

public class RankDTO implements Serializable{
	
	int titel;
	String rank;
	
	public RankDTO(int titel, String rank){
		this.titel = titel;
		this.rank = rank;
	}
	
	public int getTitel() {return titel;}
	public void setTitel(int titel) {this.titel = titel;}
	public String getRank() {return rank;}
	public void setRank(String rank) {this.rank = rank;}
	public String toString(){
		return titel + "/t" + rank + "/t";
	}
	
}
