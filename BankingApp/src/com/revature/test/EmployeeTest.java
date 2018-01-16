package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class EmployeeTest {
	
	@Test
	public final void testEmployee() throws IOException {
		String emp = "E";
		String fpass = "password";
		String log = "L";
		String user = "Cranberry";
		String pass = "123";
		String approve = "A";
		String cust = "Snow";
		String yes = "T";
		String check = "C";
		String p = "P";
		String exit = "E";
		
		String userInput = emp + System.getProperty("line.separator") + fpass + System.getProperty("line.separator") + log + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass + System.getProperty("line.separator") + approve + System.getProperty("line.separator")
				+ cust + System.getProperty("line.separator") + yes + System.getProperty("line.separator") + check + System.getProperty("line.separator") + cust +
				System.getProperty("line.separator") + p + System.getProperty("line.separator") + pass + System.getProperty("line.separator") + exit;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		LogIn.main();
	}

}
