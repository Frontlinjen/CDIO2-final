package cdiofinal.test;

import static org.junit.Assert.*;

import org.junit.Test;
import cdiofinal.shared.*;

public class FieldVerifierTest {
	
	

	@Test
	public void testIsAlphabetic() {
		assertTrue("Succes @ test 0",
				FieldVerifier.isAlphabetic("a3lphabetical")==false
				&& FieldVerifier.isAlphabetic("asd")==true
				&& FieldVerifier.isAlphabetic("test")==true
				); 
				
	}
	
	@Test
	public void testIsValidName() {
		
		assertTrue("Succes @ test 1", 
				FieldVerifier.isValidName("T")==false
				&& FieldVerifier.isValidName("Test")==true 
				&& FieldVerifier.isValidName("Testtesttesttesttesttest")==false);
	}

	@Test
	public void testIsNumber() {
		assertTrue("Succes @ test 2", 
				FieldVerifier.isNumber("k1k")==false 
				&& FieldVerifier.isNumber("2222")==true);
	}

//	@Test
//	public void testContainsNumbers() {
//		assertTrue("Succes @ test 3",
//				FieldVerifier.containsNumbers("Nam3")==true 
//				&& FieldVerifier.containsNumbers("name")==false);
//	}

	@Test
	public void testIsValidNomNetto() {
		assertTrue("Succes @ test 4", 
				FieldVerifier.isValidNomNetto(0.04)==false
				&& FieldVerifier.isValidNomNetto(0.05)==true
				&& FieldVerifier.isValidNomNetto(10.01)==true
				&& FieldVerifier.isValidNomNetto(20.0)==true
				&& FieldVerifier.isValidNomNetto(20.01)==false);
	}

	@Test
	public void testIsValidTolerance() {
		assertTrue("Succes @ test 5",
				FieldVerifier.isValidTolerance(0.09)==false
				&& FieldVerifier.isValidTolerance(0.1)==true
				&& FieldVerifier.isValidTolerance(5.0)==true
				&& FieldVerifier.isValidTolerance(10.0)==true
				&& FieldVerifier.isValidTolerance(10.01)==false);
	}

	@Test
	public void testIsValidCpr() {
		assertTrue("Succes @ test 6",
				FieldVerifier.isValidCpr("3201010101")==false
				&& FieldVerifier.isValidCpr("0113010101")==false
				&& FieldVerifier.isValidCpr("111kk00101")==false
				&& FieldVerifier.isValidCpr("010101010")==false
				&& FieldVerifier.isValidCpr("01010101010")==false
				&& FieldVerifier.isValidCpr("")==false
				&& FieldVerifier.isValidCpr("0101010101")==true
				);
	}

	@Test
	public void testIsValidIni() {
		assertTrue("Succes @ test 7",
				FieldVerifier.isValidIni("1")==false
				&& FieldVerifier.isValidIni("12")==true
				&& FieldVerifier.isValidIni("123")==true
				&& FieldVerifier.isValidIni("1234")==true
				&& FieldVerifier.isValidIni("12345")==false);
	}

	@Test
	public void testIsValidId() {
		assertTrue("Succes @ test 8",
				FieldVerifier.isValidId(0)==false
				&& FieldVerifier.isValidId(8)==true);
	}

	@Test
	public void testIsValidPassword() {
		assertTrue("Succes @ test 9",
				FieldVerifier.isValidPassword("t")==false
				&& FieldVerifier.isValidPassword("test")==true
				&& FieldVerifier.isValidPassword("testpa")==true
				&& FieldVerifier.isValidPassword("testpas")==true
				&& FieldVerifier.isValidPassword("testpassword")==false);
	}

}
