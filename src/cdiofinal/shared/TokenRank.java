package cdiofinal.shared;

import java.io.Serializable;

public class TokenRank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int rank;
	String token;
	public TokenRank()
	{
		
	}
	public TokenRank(int rank, String token){
		this.rank = rank;
		this.token = token;
	}
	
	public int getRank(){
		return rank;
	}
	
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
}
