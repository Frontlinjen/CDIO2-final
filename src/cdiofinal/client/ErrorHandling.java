package cdiofinal.client;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
	
import cdiofinal.shared.InsufficientAccessException;

public class ErrorHandling {

	public static String getError (Throwable caught){
		try {
		       throw caught;
		     } catch (IncompatibleRemoteServiceException e) {
		       return ("Incompatible");
		     } catch (InvocationException e) {
		       return("Failed to connect to server\n" + e.getMessage());
		     } catch(InsufficientAccessException e)
			{
				    return(e.getMessage()); 
			 }
		 	catch (Throwable e) {
		       // last resort -- a very unexpected exception
		    	 return(e.getMessage());
		     }
	}
}
