package cdiofinal.shared;

import java.io.Serializable;

public class LeverandoerDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int leverandoerId;             // i omraadet 1-99999999
	private String leverandoerNavn;

	public LeverandoerDTO()
	{
		
	}
	
	public boolean isValid()
	{
		if(FieldVerifier.isValidId(this.getLeverandoerId())==true 
		&& FieldVerifier.isValidName(this.getLeverandoerNavn())==true){
			return true;
		}
		else return false;
	}
	
	public LeverandoerDTO(int leverandoerId, String leverandoerNavn)
	{
		this.leverandoerId = leverandoerId;
		this.leverandoerNavn = leverandoerNavn;
	}
	
	public int getLeverandoerId() { return leverandoerId; }
	public void setLeverandoerId(int leverandoerId) { this.leverandoerId = leverandoerId; }
	public String getLeverandoerNavn() { return leverandoerNavn; }
	public void setLeverandoerNavn(String leverandoerNavn) { this.leverandoerNavn = leverandoerNavn; }
	@Override
	public String toString() { 
		return leverandoerId + "\t" + leverandoerNavn; 
	}
}
