package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class AdminRegister {
	
	@Test
	public final void registerAdmin() throws IOException{
		String emp = "A";
		String fpass = "superpassword";
		String log = "R";
		String user = "Fav";
		String pass = "123";
		
		String userInput = emp + System.getProperty("line.separator") + fpass + System.getProperty("line.separator") + log + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		LogIn.main();
	}
	

}
