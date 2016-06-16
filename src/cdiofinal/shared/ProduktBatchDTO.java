package cdiofinal.shared;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProduktBatchDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pbId;                     // i omraadet 1-99999999
	private int status;
	private int receptId;
	private Timestamp startDate;
	private Timestamp endDate;
	
	public ProduktBatchDTO()
	{
		
	}
	
	public boolean isValid(){
		if(FieldVerifier.isValidId((this.getReceptId()))==true 
		&& FieldVerifier.isValidId(this.getPbId())==true
		&& FieldVerifier.isValidStatus(this.status)==true){
			return true;
		}
		else return false;
	}
	
	public ProduktBatchDTO(int pbId, int status, int receptId)
	{
		this(pbId, status, receptId, null, null);
	}
	public ProduktBatchDTO(int pbId, int status, int receptId, Timestamp start, Timestamp end)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
		startDate = start;
		endDate = end;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int i) { this.status = i; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() { return pbId + "\t" + status + "\t" + receptId; }
}

