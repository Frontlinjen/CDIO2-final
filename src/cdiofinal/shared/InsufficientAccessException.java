package cdiofinal.shared;

import java.io.Serializable;

public class InsufficientAccessException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientAccessException(String s)
	{
		super(s);
	}
	

}
