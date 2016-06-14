package cdiofinal.shared;

import java.io.Serializable;

public class TokenRank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rank;
	private String token;
	private String name;
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
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
