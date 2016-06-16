package cdiofinal.shared;

import java.io.Serializable;

public class RaavareBatchDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int raavarebatchId;
	int raavareId;
	int leverandoerId;
	double maengde;
	
	public RaavareBatchDTO(){
		
	}
	
	public boolean isValid(){
		if(FieldVerifier.isValidId(this.getRaavarebatchId()) 
		&& FieldVerifier.isValidId(this.getRaavareId())==true
		&& FieldVerifier.isValidId(this.getLeverandoerId())==true
		&& FieldVerifier.isNumber(Double.toString(this.maengde))){
			return true;
		}
		else return false;
	}
	
	public RaavareBatchDTO(int raavarebatchId, int raavareId, int leverandoerId, double maengde){
		this.raavarebatchId = raavarebatchId;
		this.raavareId = raavareId;
		this.leverandoerId = leverandoerId;
		this.maengde = maengde;
	}
	
	public int getRaavarebatchId() {return raavarebatchId;}
	public void setRaavarebatchId(int raavarebatchId) {this.raavarebatchId = raavarebatchId;}
	public int getRaavareId() {return raavareId;}
	public void setRaavareId(int raavareId) {this.raavareId = raavareId;}
	public int getLeverandoerId() {return leverandoerId;}
	public void setLeverandoerId(int leverandoerId) {this.leverandoerId = leverandoerId;}
	public double getMaengde() {return maengde;}
	public void setMaengde(double maengde) {this.maengde = maengde;}
	@Override
	public String toString(){
		return raavarebatchId + "/t" + raavareId + "/t" + leverandoerId + "/t" + maengde;
	}
}
