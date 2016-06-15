package cdiofinal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cdiofinal.shared.FieldVerifier;

public class FieldVerifierTest {
	
	FieldVerifier FV = new FieldVerifier();

	@Test
	public void testIsValidName() {
		assertTrue("Failed test1",);
	}

	@Test
	public void testIsNumber() {
		assertTrue("Failed test2",);
	}

	@Test
	public void testContainsNumbers() {
		assertTrue("Failed test3",);
	}

	@Test
	public void testIsValidNomNetto() {
		assertTrue("Failed test4",);
	}

	@Test
	public void testIsValidTolerance() {
		assertTrue("Failed test5",);
	}

	@Test
	public void testIsValidCpr() {
		assertTrue("Failed test6",);
	}

	@Test
	public void testIsValidIni() {
		assertTrue("Failed test7",);
	}

	@Test
	public void testIsValidId() {
		assertTrue("Failed test8",);
	}

	@Test
	public void testIsValidPassword() {
		assertTrue("Failed test9",);
	}

}
