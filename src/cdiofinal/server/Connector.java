package cdiofinal.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cdiofinal.shared.DALException;


public class Connector
{
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */

	private static Connection conn;
	private static Statement stm;

	public static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException
	{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return DriverManager.getConnection(url, username, password);
	}

	public Connector(String server, int port, String database,
			String username, String password)
					throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{
		conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
				username, password);
		stm		= conn.createStatement();
	}

	public Connector() throws InstantiationException, IllegalAccessException,
	ClassNotFoundException, SQLException
	{
		this(Constant.server, Constant.port, Constant.database,
				Constant.username, Constant.password);
	}

	public static ResultSet doQuery(String cmd) throws SQLException
	{
		connector();
		return stm.executeQuery(cmd); 
	}

	public static int doUpdate(String cmd) throws SQLException
	{
		connector();
		return stm.executeUpdate(cmd); 

	}

	private static void connector() throws SQLException {
			if(conn==null)
			{
				try {
					new Connector();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					System.err.println("Failed to setup MySQL!");
					e.printStackTrace();
				}
			}
		

	}


}