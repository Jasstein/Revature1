package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	void testSetUserName() {
		Employee e = new Employee();
		e.setUserName("Bob");
		String test = e.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testSetPass() {
		Employee e = new Employee();
		e.setPass("123");
		String test = e.getPass();
		assertEquals("123", test);
	}

	@Test
	void testGetUserName() {
		Employee e = new Employee();
		e.setUserName("Bob");
		String test = e.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testGetPass() {
		Employee e = new Employee();
		e.setPass("123");
		String test = e.getPass();
		assertEquals("123", test);
	}

	@Test
	void testIsValid() {
		Employee e = new Employee();
		e.setUserName("Bob");
		e.setPass("123");
		boolean test = e.isValid("Bob", "123");
		assertTrue(test);
	}

	@Test
	void testGetCustomerInfoStringArrayListOfCustomer() {
		Customer c = new Customer();
		ArrayList<Customer> cList = new ArrayList<Customer>();
		c.setUserName("Bob");
		cList.add(c);
		Employee e = new Employee();
		Customer test = e.getCustomerInfo("Bob", cList);
		assertEquals(c, test);
	}

	@Test
	void testGetCustomerInfoStringListOfCustomer() {
		Customer c = new Customer();
		List<Customer> cList = new ArrayList<Customer>();
		c.setUserName("Bob");
		cList.add(c);
		Employee e = new Employee();
		Customer test = e.getCustomerInfo("Bob", cList);
		assertEquals(c, test);
	}

	@Test
	void testGetAccountInfoIntArrayListOfAccount() {
		Account a = new Account();
		ArrayList<Account> aList = new ArrayList<Account>();
		a.setAccountNumber(123);
		aList.add(a);
		Employee e = new Employee();
		Account test = e.getAccountInfo(123, aList);
		assertEquals(a, test);
	}

	@Test
	void testGetAccountInfoIntListOfAccount() {
		Account a = new Account();
		List<Account> aList = new ArrayList<Account>();
		a.setAccountNumber(123);
		aList.add(a);
		Employee e = new Employee();
		Account test = e.getAccountInfo(123, aList);
		assertEquals(a, test);
	}

	@Test
	void testPrintCustInfo() {
		Employee e = new Employee();
		Customer c = new Customer();
		Account a = new Account();
		a.setAccountNumber(100);
		c.setUserName("Bob");
		c.setPass("123");
		c.setAccountNum(100);
		e.printCustInfo(c, a);
	}

}
