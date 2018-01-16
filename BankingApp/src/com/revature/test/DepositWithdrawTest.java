package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class DepositWithdrawTest {
	
	@Test
	public final void testDepositWithdraw() throws IOException {
		String log = "L";
		String user = "Snow";
		String pass = "123";
		String d = "D";
		double depo = 10;
		String w = "W";
		double with = 5;
		String exit = "E";
		String t = "T";
		String target = "Mina";
		String p = "P";
		
		String userInput = log + System.getProperty("line.separator") + user + System.getProperty("line.separator") + pass + 
				System.getProperty("line.separator") + d + System.getProperty("line.separator") + depo + System.getProperty("line.separator")
				+ w + System.getProperty("line.separator") + with + System.getProperty("line.separator")
				+ t + System.getProperty("line.separator") + target + System.getProperty("line.separator") + 
				1 + System.getProperty("line.separator") + p + System.getProperty("line.separator")
				+ pass + System.getProperty("line.separator") + exit;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		
		LogIn.main();
	}

}
