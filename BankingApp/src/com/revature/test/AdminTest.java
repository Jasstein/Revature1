package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class AdminTest {
	
	@Test
	public final void testAdmin() throws IOException{
		String ad = "A";
		String spass = "superpassword";
		String log  = "L";
		String admin = "Fav";
		String pass = "123";
		String check = "C";
		double num = 10;
		double num2 = 11;
		String d = "D";
		String w = "W";
		String t = "T";
		String p = "P";
		String cancel = "CA";
		String cust = "Snow";
		String cust2 = "Mina";
		String exit = "E";
		
		String userInput = ad + System.getProperty("line.separator") + spass + System.getProperty("line.separator") + log + 
				System.getProperty("line.separator") + admin +	System.getProperty("line.separator") + pass + System.getProperty("line.separator") + 
				check + System.getProperty("line.separator") + cust + System.getProperty("line.separator") +
				d + System.getProperty("line.separator") + cust + System.getProperty("line.separator") + num2 + System.getProperty("line.separator")
				+ w + System.getProperty("line.separator") +  cust + System.getProperty("line.separator") + num + System.getProperty("line.separator") +
				t + System.getProperty("line.separator") + cust2 + System.getProperty("line.separator") + 
				1 + System.getProperty("line.separator") + p + System.getProperty("line.separator")
				+ pass + System.getProperty("line.separator") +
				cancel + System.getProperty("line.separator") + cust + System.getProperty("line.separator") +
				cancel + System.getProperty("line.separator") + cust2 + System.getProperty("line.separator") + exit;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		
		LogIn.main();

	}

}
