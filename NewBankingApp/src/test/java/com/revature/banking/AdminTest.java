package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AdminTest {

	@Test
	void testSetUserName() {
		Admin a = new Admin();
		a.setUserName("Bob");
		String test = a.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testSetPass() {
		Admin a = new Admin();
		a.setPass("123");
		String test = a.getPass();
		assertEquals("123", test);
	}

	@Test
	void testGetUserName() {
		Admin a = new Admin();
		a.setUserName("Bob");
		String test = a.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testGetPass() {
		Admin a = new Admin();
		a.setPass("123");
		String test = a.getPass();
		assertEquals("123", test);
	}

	@Test
	void testIsValid() {
		Admin a = new Admin();
		a.setUserName("Bob");
		a.setPass("123");
		boolean test = a.isValid("Bob", "123");
		assertTrue(test);
	}

	@Test
	void testGetEmployeeInfoStringArrayListOfEmployee() {
		Employee e = new Employee();
		ArrayList<Employee> eList = new ArrayList<Employee>();
		e.setUserName("Bob");
		eList.add(e);
		Admin a = new Admin();
		Employee test = a.getEmployeeInfo("Bob", eList);
		assertEquals(e, test);
	}

	@Test
	void testGetEmployeeInfoStringListOfEmployee() {
		Employee e = new Employee();
		List<Employee> eList = new ArrayList<Employee>();
		e.setUserName("Bob");
		eList.add(e);
		Admin a = new Admin();
		Employee test = a.getEmployeeInfo("Bob", eList);
		assertEquals(e, test);
	}

	@Test
	void testGetAdminInfoStringArrayListOfAdmin() {
		Admin d = new Admin();
		ArrayList<Admin> aList = new ArrayList<Admin>();
		d.setUserName("Bob");
		aList.add(d);
		Admin a = new Admin();
		Admin test = a.getAdminInfo("Bob", aList);
		assertEquals(d, test);
	}

	@Test
	void testGetAdminInfoStringListOfAdmin() {
		Admin d = new Admin();
		List<Admin> aList = new ArrayList<Admin>();
		d.setUserName("Bob");
		aList.add(d);
		Admin a = new Admin();
		Admin test = a.getAdminInfo("Bob", aList);
		assertEquals(d, test);
	}

}
