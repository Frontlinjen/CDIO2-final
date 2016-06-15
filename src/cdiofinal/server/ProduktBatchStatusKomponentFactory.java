package cdiofinal.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cdiofinal.shared.ProduktBatchKompDTO;
import cdiofinal.shared.RaavareDTO;
import cdiofinal.shared.ReceptDTO;
import cdiofinal.shared.ReceptKompDTO;

public class ProduktBatchStatusKomponentFactory {
	private String layout;
	public ProduktBatchStatusKomponentFactory() throws FileNotFoundException
	{
		File f = new File("printpbkomponentlayout.html");
		layout = loadFile(f);
	}
	public ProduktBatchStatusKomponent getPbKomponent(RaavareDTO raavare, ReceptKompDTO receptKomp,ProduktBatchKompDTO pbBatch)
	{
		
		return new ProduktBatchStatusKomponent(raavare, receptKomp, pbBatch, new String(layout));
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
