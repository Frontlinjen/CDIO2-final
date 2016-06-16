package cdiofinal.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdiofinal.shared.DALException;

public class ProduktBatchStatusServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter stream = resp.getWriter();
		String param = req.getParameter("pbID");
		try {
			int pb = Integer.parseInt(param);
			ProduktBatchStatusBody pdBody = new ProduktBatchStatusBody(pb);
			stream.write(pdBody.getLayout());
		} catch (NumberFormatException e){
			stream.println("Param must be an integer");
		}
		catch (DALException e) {
			stream.write(e.getMessage());
		} catch (IOException e)
		{
			stream.write("Internal server error: " + e.getMessage());
		}
		
				
	}

}
