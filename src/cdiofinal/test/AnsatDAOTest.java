package cdiofinal.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import cdiofinal.shared.*;
import cdiofinal.server.*;

public class AnsatDAOTest {

	//BEFORE THIS TEST IS RUN = RUN THE TWO DATABASE PROCEDURES TO RESET THE DATA.
	//ELSE IT WILL FAIL THE FIRST AND THIRD TEST
	
	@Before
	 public void initialize() {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testGetAnsat() throws DALException{
		AnsatDTO ansat = new AnsatDTO("1102990109", "Bob", "Bo", "fisken", 0);
		MySQLAnsatDAO ans = new MySQLAnsatDAO();
		ans.createAnsat(ansat);
		assertTrue
			("Succesful retrievel of the employee: ", 
					ans.getAnsat("1102990109").getOprNavn().equals("Bob") &&
					ans.getAnsat("1102990109").getIni().equals("Bo") &&
					ans.getAnsat("1102990109").getCpr().equals("1102990109") &&
					ans.getAnsat("1102990109").getTitel() == 0 &&
					ans.getAnsat("1102990109").getPassword().equals("fisken"));
	}
	
	@Test
	public void testCreateAnsat()throws DALException{
		MySQLAnsatDAO ansDAO = new MySQLAnsatDAO();
		AnsatDTO ans = new AnsatDTO("1102990185", "Bob", "Bo", "fisken",0);
		ansDAO.createAnsat(ans);
		assertTrue
			("Succes creating the employee with the CPR: 1102990185", 
			ansDAO.getAnsat("1102990185").getOprNavn().equals("Bob") &&
			ansDAO.getAnsat("1102990185").getIni().equals("Bo") &&
			ansDAO.getAnsat("1102990185").getCpr().equals("1102990185") &&
			ansDAO.getAnsat("1102990185").getTitel() == 0 &&
			ansDAO.getAnsat("1102990185").getPassword().equals("fisken"));
	}
	
	@Test
	public void updateAnsat() throws DALException{	
	AnsatDTO ans1 = new AnsatDTO("1102990185", "Tim", "Ti", "Tims",1);
	MySQLAnsatDAO ansDAO = new MySQLAnsatDAO();
	ansDAO.getAnsat("1102990185");
	ansDAO.updateAnsat(ans1);
	assertFalse("Fail, the info are no longer applying to '1102990185' ", 
			ansDAO.getAnsat("1102990185").getOprNavn().equals("Bob") &&
			ansDAO.getAnsat("1102990185").getIni().equals("Bo") &&
			ansDAO.getAnsat("1102990185").getPassword().equals("fisken") &&
			ansDAO.getAnsat("1102990185").getTitel()==0);
	}

	@Test 
	public void getAnsatList() throws DALException{
		MySQLAnsatDAO ansatDAO = new MySQLAnsatDAO();
		assertFalse("Fail, the list is not empty: ", ansatDAO.getAnsatList()==null);
	}
}
