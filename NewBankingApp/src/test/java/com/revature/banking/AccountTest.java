package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void testSetAccountNumber() {
		Account a = new Account();
		a.setAccountNumber(123);
		int test = a.getAccountNumber();
		assertEquals(123,test);
	}

	@Test
	void testSetCheckingAmt() {
		Account a = new Account();
		a.setCheckingAmt(10);
		double test = a.getCheckingAmt();
		assertEquals(10,test);
	}

	@Test
	void testSetApproved() {
		Account a  = new Account();
		a.setApproved(true);
		boolean test = a.getApproved();
		assertTrue(test);
	}

	@Test
	void testSetJoint() {
		Account a = new Account();
		a.setJoint(true);
		boolean test = a.getJoint();
		assertTrue(test);
	}

	@Test
	void testGetAccountNumber() {
		Account a = new Account();
		a.setAccountNumber(123);
		int test = a.getAccountNumber();
		assertEquals(123,test);
	}

	@Test
	void testGetCheckingAmt() {
		Account a = new Account();
		a.setCheckingAmt(10);
		double test = a.getCheckingAmt();
		assertEquals(10,test);
	}

	@Test
	void testGetApproved() {
		Account a  = new Account();
		a.setApproved(true);
		boolean test = a.getApproved();
		assertTrue(test);
	}

	@Test
	void testGetJoint() {
		Account a = new Account();
		a.setJoint(true);
		boolean test = a.getJoint();
		assertTrue(test);
	}

}
