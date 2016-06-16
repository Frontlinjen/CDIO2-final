package cdiofinal.shared;

import java.io.Serializable;

public class ReceptKompDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int receptId;                  // auto genereres fra 1..n   
	private int raavareId;             // i omraadet 1-99999999
	private double nomNetto;            // skal vaere positiv og passende stor
	private double tolerance;           // skal vaere positiv og passende stor
	
	public ReceptKompDTO()
	{
		
	}
	
	public boolean isValid()
	{
		if (FieldVerifier.isValidId(this.receptId)==true && FieldVerifier.isValidId(this.raavareId)==true
			&& FieldVerifier.isValidNomNetto(this.nomNetto)== true && FieldVerifier.isValidTolerance(this.tolerance) ==true) {
			return true;
		}
		else return false;
	}
	
	public ReceptKompDTO(int receptId, int raavareId, double nomNetto, double tolerance)
	{
		this.receptId = receptId;
		this.raavareId = raavareId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public int getRaavareId() { return raavareId; }
	public void setRaavareId(int raavareId) { this.raavareId = raavareId; }
	public double getNomNetto() { return nomNetto; }
	public void setNomNetto(double nomNetto) { this.nomNetto = nomNetto; }
	public double getTolerance() { return tolerance; }
	public void setTolerance(double tolerance) { this.tolerance = tolerance; }
	@Override
	public String toString() { 
		return receptId + "\t" + raavareId + "\t" + nomNetto + "\t" + tolerance; 
	}
}
