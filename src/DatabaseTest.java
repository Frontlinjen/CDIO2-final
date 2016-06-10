import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cdiofinal.server.AnsatDAO;
import cdiofinal.server.MySQLAnsatDAO;
import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.DALException;

public class DatabaseTest {
	
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		try {
			AnsatDAO aDAO = new MySQLAnsatDAO();
			System.out.println("Getting list..");
			List<AnsatDTO> list = aDAO.getAnsatList();
			for (AnsatDTO ansatDTO : list) {
				System.out.println(ansatDTO);
			}
		} catch(DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
