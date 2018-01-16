package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class RegisterEmployee {
	
	@Test
	public final void registerEmployee() throws IOException {
		String emp = "E";
		String fpass = "password";
		String log = "R";
		String user = "Cranberry";
		String pass = "123";
		
		String userInput = emp + System.getProperty("line.separator") + fpass + System.getProperty("line.separator") + log + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		LogIn.main();
	}

}
