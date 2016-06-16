package cdiofinal.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cdiofinal.shared.DALException;
import cdiofinal.shared.ProduktBatchDTO;
import cdiofinal.shared.ProduktBatchKompDTO;
import cdiofinal.shared.RaavareBatchDTO;
import cdiofinal.shared.ReceptKompDTO;

public class ProduktBatchStatusBody {
	private ProduktBatchDAO produktbatches = new MySQLProduktbatchDAO();
	private String fileContent;
	private double taraSum;
	private double nettoSum;
	private ProduktBatchDTO pbBatch;
	private String[] batchStatuses = {"Oprettet", "Startet", "Afsluttet"};
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss, EEEE, d/MM/yy");
	public ProduktBatchStatusBody(int pbID) throws FileNotFoundException, DALException
	{
		pbBatch = produktbatches.getProduktBatch(pbID);
		if(pbBatch==null)
		{
			throw new DALException("pbID dont exists");
		}
		File file = new File("printpblayout.html");
		fileContent = loadFile(file);
		if(fileContent!=null)
		{
			
			addDate();
			fileContent = fileContent.replace("PLACEHOLDER_PD_BATCH_NR", Integer.toString(pbID));
			fileContent = fileContent.replace("PLACEHOLDER_RECEPT_NR", Integer.toString(pbBatch.getReceptId()));
			fileContent = fileContent.replace("PLACEHOLDER_PROD_STATUS", batchStatuses[pbBatch.getStatus()-1]);
			addKomponents();
		}
	}
	private void addKomponents() throws FileNotFoundException
	{
		System.out.println("adding components..");
		ProduktBatchStatusKomponentFactory factory = new ProduktBatchStatusKomponentFactory();
		ReceptKompDAO recepts = new MySQLReceptKompDAO();
		RaavareBatchDAO raavareBatches = new MySQLRaavareBatchDAO();
		ProduktBatchKompDAO pBatches = new MySQLProduktBatchKompDAO();
		RaavareDAO raavare = new MySQLRaavareDAO();
		try {
			System.out.println("Getting ingredients for " + pbBatch.getReceptId());
			List<ReceptKompDTO> ingredients = recepts.getReceptKompList(pbBatch.getReceptId());
			List<ProduktBatchKompDTO> addedIngredientsList = pBatches.getProduktBatchKompList(pbBatch.getPbId());
			
			Map<Integer, ProduktBatchKompDTO> addedIngredients = new HashMap<Integer, ProduktBatchKompDTO>();
			//Adds the ingredients to a map for ease of lookup
			for (ProduktBatchKompDTO value : addedIngredientsList) {
				RaavareBatchDTO batch = raavareBatches.getRaavareBatch(value.getRaavarebatchId());
				addedIngredients.put(batch.getRaavareId(), value);
				taraSum += value.getTara();
				nettoSum += value.getNetto();
			}
			String body = "";
			for (ReceptKompDTO ingredient : ingredients) {
				System.out.println("Adding ingredient: " + ingredient.getRaavareId());
				ProduktBatchStatusKomponent bodyPart = factory.getPbKomponent(raavare.getRaavare(ingredient.getRaavareId()), ingredient, addedIngredients.get(ingredient.getRaavareId()));
				body += bodyPart.getLayout() + "<br>";
			}
			DecimalFormat df = new DecimalFormat("0.0000"); 
			fileContent = fileContent.replace("PLACEHOLDER_RECEPT_KOMPONENTS", body);
			fileContent = fileContent.replace("PLACEHOLDER_SUM_TARA", df.format(taraSum));
			fileContent = fileContent.replace("PLACEHOLDER_SUM_NETTO", df.format(nettoSum));
			
			Date startDate = pbBatch.getStartDate();
			Date endDate = pbBatch.getEndDate();
			if(startDate!=null)
			{
				fileContent = fileContent.replace("PLACEHOLDER_PROD_STARTET", dateFormatter.format(startDate));
			}
			else
			{
				fileContent = fileContent.replace("PLACEHOLDER_PROD_STARTET", "Ikke startet");
			}
			if(endDate!=null)
			{
				fileContent = fileContent.replace("PLACEHOLDER_PROD_ENDED", dateFormatter.format(endDate));
			}
			else
			{
				fileContent = fileContent.replace("PLACEHOLDER_PROD_ENDED", "Ikke afsluttet");
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getLayout()
	{
		return fileContent;
	}
	private void addDate()
	{
		Date date = new Date();
		if(date!=null)
		{
				String currDate = dateFormatter.format(date);
				if(currDate!=null)
				{
					fileContent = fileContent.replace("PLACEHOLDER_PRINT_DATE", currDate);
				}
				else
				{
					fileContent = fileContent.replace("PLACEHOLDER_PRINT_DATE", "Ukendt");
				}
		}
		
	}
	
	
	private String loadFile(File file) throws FileNotFoundException
	{
		FileInputStream reader = new FileInputStream(file);
		byte[] content = new byte[(int)file.length()];
		try {
			reader.read(content);
			reader.close();
			return new String(content, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 			
	}
}
