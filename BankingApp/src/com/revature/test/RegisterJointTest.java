package com.revature.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.revature.banking.LogIn;

public class RegisterJointTest {
	
	@Test
	public final void test2Register() throws IOException {
		String reg = "R";
		String joint = "N";
		String user = "Mina";
		String pass = "123";
		String user2 = "Yuna";
		String jointY = "Y";
		
		String userInput = reg + System.getProperty("line.separator") + joint + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass;
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		
		LogIn.main();
		
		String userInput2 = reg + System.getProperty("line.separator") + jointY + System.getProperty("line.separator") + user2
				+ System.getProperty("line.separator") + pass + System.getProperty("line.separator") + user
				+ System.getProperty("line.separator") + pass;
		
		InputStream in2 = new ByteArrayInputStream(userInput2.getBytes());
		System.setIn(in2);
		Scanner scanner2 = new Scanner(System.in);
		
		LogIn.main();
	}

}
