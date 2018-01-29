package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testSetUserName() {
		Customer c = new Customer();
		c.setUserName("Bob");
		String test = c.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testSetPass() {
		Customer c = new Customer();
		c.setPass("123");
		String test = c.getPass();
		assertEquals("123", test);
	}

	@Test
	void testGetUserName() {
		Customer c = new Customer();
		c.setUserName("Bob");
		String test = c.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testGetPass() {
		Customer c = new Customer();
		c.setPass("123");
		String test = c.getPass();
		assertEquals("123", test);
	}

	@Test
	void testSetAccountNum() {
		Customer c = new Customer();
		c.setAccountNum(5);
		int test = c.getAccountNum();
		assertEquals(5, test);
	}

	@Test
	void testGetAccountNum() {
		Customer c = new Customer();
		c.setAccountNum(5);
		int test = c.getAccountNum();
		assertEquals(5, test);
	}

	@Test
	void testIsValid() {
		Customer c = new Customer();
		c.setUserName("Bob");
		c.setPass("123");
		boolean test = c.isValid("Bob", "123");
		assertTrue(test);
	}

	@Test
	void testWithdraw() {
		Customer c = new Customer();
		Account a = new Account();
		a.setCheckingAmt(10);
		String userInput = "5";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		c.withdraw(a);
		double test = a.getCheckingAmt();
		assertEquals(5, test);
	}

	@Test
	void testDeposit() {
		Customer c = new Customer();
		Account a = new Account();
		a.setCheckingAmt(0);
		String userInput = "10";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		c.deposit(a);
		double test = a.getCheckingAmt();
		assertEquals(10, test);
	}

	@Test
	void testTransfer() {
		Customer c = new Customer();
		Account a = new Account();
		a.setCheckingAmt(10);
		Account b = new Account();
		b.setCheckingAmt(0);
		String userInput = "5";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		c.transfer(a, b);
		double test = a.getCheckingAmt();
		assertEquals(5, test);
		double test2 = b.getCheckingAmt();
		assertEquals(5,test2);
	}

	@Test
	void testCheck_account() {
		Customer c = new Customer();
		Account a = new Account();
		c.check_account(a);
	}

}
