package cdiofinal.shared;

public class ProduktBatchKompDTO 
{
	int pbId; 	  // produktbatchets id
	int raavareId;        // raavarens id
	double tara;
	double netto;
	String cpr;					// cpr-nummer for operatøren

	
	public ProduktBatchKompDTO(int pbId, int raavareId, double tara, double netto, String cpr)
	{
		this.pbId = pbId;
		this.raavareId = raavareId;
		this.tara = tara;
		this.netto = netto;
		this.cpr = cpr;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getRaavareId() { return raavareId; }
	public void setRaavareId(int raavareId) { this.raavareId = raavareId; }
	public double getTara() { return tara; }
	public void setTara(double tara) { this.tara = tara; }
	public double getNetto() { return netto; }
	public void setNetto(double netto) { this.netto = netto; }
	public String getCpr() { return cpr; }
	public void setCpr(String cpr) { this.cpr = cpr; }
	public String toString() { 
		return pbId + "\t" + raavareId +"\t" + tara +"\t" + netto + "\t" + cpr ; 
	}
}
