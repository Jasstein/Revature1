package com.revature.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnectionUtilTest {

	@Test
	void testGetInstance() {
		ConnectionUtil test = ConnectionUtil.getInstance();
		assertNotNull(test);
	}

	@Test
	void testGetConnection() {
		ConnectionUtil c = ConnectionUtil.getInstance();
		Connection test = c.getConnection();
		assertNotNull(test);
	}

}
