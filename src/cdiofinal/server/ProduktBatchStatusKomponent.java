package cdiofinal.server;

import cdiofinal.shared.ProduktBatchKompDTO;
import cdiofinal.shared.RaavareDTO;
import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

public class ProduktBatchStatusKomponent {
	
	private String layout;
	public ProduktBatchStatusKomponent(RaavareDTO raavare, ReceptKompDTO receptKomp, ProduktBatchKompDTO pdBatch, String layoutTemplate)
	{
		System.out.println("Creating new statuskomponent");
		this.layout = layoutTemplate;
		layout = layout.replace("PLACEHOLDER_RAAVARE_NR", Integer.toString(raavare.getRaavareId()));
		layout = layout.replace("PLACEHOLDER_RAAVARE_NAME", raavare.getRaavareNavn());
		layout = layout.replace("PLACEHOLDER_AMOUNT", Double.toString(receptKomp.getNomNetto()));
		layout = layout.replace("PLACEHOLDER_TOLERANCE", Double.toString(receptKomp.getTolerance()));
		layout = layout.replace("PLACEHOLDER_PART", "1");
		if(pdBatch!=null)
		{
			layout = layout.replace("PLACEHOLDER_TARA", Double.toString(pdBatch.getTara()));
			layout = layout.replace("PLACEHOLDER_NETTO", Double.toString(pdBatch.getNetto()));
			layout = layout.replace("PLACEHOLDER_BATCH", Integer.toString(pdBatch.getRaavarebatchId()));
			layout = layout.replace("PLACEHOLDER_OPERATOR", pdBatch.getCpr());
			layout = layout.replace("PLACEHOLDER_TERMINAL", "1");
		}
		else
		{
			layout = layout.replace("PLACEHOLDER_TARA", "");
			layout = layout.replace("PLACEHOLDER_NETTO", "");
			layout = layout.replace("PLACEHOLDER_BATCH", "");
			layout = layout.replace("PLACEHOLDER_OPERATOR", "");
			layout = layout.replace("PLACEHOLDER_TERMINAL", "");
		}
		System.out.println("Statuskomponent created");
	}
	
	public String getLayout()
	{
		return layout;
	}

}
