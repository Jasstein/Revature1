package com.revature.driver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class LogInSQLTest {

	@SuppressWarnings("static-access")
	@Test
	void testMainStringArray() throws IOException {
		LogInSQL test = new LogInSQL();
		String args[] = null;
		String userInput = "X";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		test.main(args);
	}

	@Test
	void testMain() throws IOException {
		String userInput = "X";
		InputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		LogInSQL.main();
	}

}
