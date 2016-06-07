package cdiofinal.shared;

public class LeverandoerDTO
{
	int raavareId;             // i omraadet 1-99999999
	String leverandoerNavn;
	double maengde;             // kan vaere negativ 

	public LeverandoerDTO(int raavareId, String leverandoerNavn, double maengde)
	{
		this.raavareId = raavareId;
		this.leverandoerNavn = leverandoerNavn;
		this.maengde = maengde;
	}
	
	public int getRaavareId() { return raavareId; }
	public void setRaavareId(int raavareId) { this.raavareId = raavareId; }
	public String getLeverandoerNavn() { return leverandoerNavn; }
	public void setLeverandoerNavn(String leverandoerNavn) { this.leverandoerNavn = leverandoerNavn; }
	public double getMaengde() { return maengde; }
	public void setMaengde(double maengde) { this.maengde = maengde; }
	public String toString() { 
		return raavareId + "\t" + leverandoerNavn + "\t" + maengde; 
	}
}
