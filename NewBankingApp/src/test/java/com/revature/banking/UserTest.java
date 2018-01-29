package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testSetUserName() {
		Customer u = new Customer();
		u.setUserName("Bob");
		String test = u.getUserName();
		assertEquals("Bob", test);
		
	}

	@Test
	void testSetPass() {
		Customer u = new Customer();
		u.setPass("123");
		String test = u.getPass();
		assertEquals("123", test);
	}

	@Test
	void testGetUserName() {
		Customer u = new Customer();
		u.setUserName("Bob");
		String test = u.getUserName();
		assertEquals("Bob", test);
	}

	@Test
	void testGetPass() {
		Customer u = new Customer();
		u.setPass("123");
		String test = u.getPass();
		assertEquals("123", test);
	}

	@Test
	void testChangePass() {
		Customer u = new Customer();
		u.setPass("123");
		String userInput = "456";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		u.changePass();
		String test = u.getPass();
		assertEquals("456", test);
	}

}
