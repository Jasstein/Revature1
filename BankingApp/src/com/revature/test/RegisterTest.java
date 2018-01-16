package com.revature.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class RegisterTest {

	@Test
	public final void testRegister() throws IOException {
		String reg = "R";
		String joint = "N";
		String user = "Snow";
		String pass = "123";
		
		String userInput = reg + System.getProperty("line.separator") + joint + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass;
		
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		
		LogIn.main();
	}	

}

