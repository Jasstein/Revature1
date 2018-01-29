package com.revature.banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditTest {

	@Test
	void testSetAuditId() {
		Audit a = new Audit();
		a.setAuditId(5);
		int test = a.getAuditId();
		assertEquals(5, test);
	}

	@Test
	void testSetAccountNum() {
		Audit a = new Audit();
		a.setAccountNum(123);
		int test = a.getAccountNum();
		assertEquals(123, test);
	}

	@Test
	void testSetCheckingChange() {
		Audit a = new Audit();
		a.setCheckingChange(10);
		double test = a.getCheckingChange();
		assertEquals(10, test);
	}

	@Test
	void testSetOldApproved() {
		Audit a = new Audit();
		a.setOldApproved(true);
		boolean test = a.getOldApproved();
		assertTrue(test);
	}

	@Test
	void testSetNewApproved() {
		Audit a = new Audit();
		a.setNewApproved(true);
		boolean test = a.getNewApproved();
		assertTrue(test);
	}

	@Test
	void testSetOldJoint() {
		Audit a = new Audit();
		a.setOldJoint(true);
		boolean test = a.getOldJoint();
		assertTrue(test);
	}

	@Test
	void testSetNewJoint() {
		Audit a = new Audit();
		a.setNewJoint(true);
		boolean test = a.getNewJoint();
		assertTrue(test);
	}

	@Test
	void testGetAuditId() {
		Audit a = new Audit();
		a.setAuditId(5);
		int test = a.getAuditId();
		assertEquals(5, test);
	}

	@Test
	void testGetAccountNum() {
		Audit a = new Audit();
		a.setAccountNum(123);
		int test = a.getAccountNum();
		assertEquals(123, test);
	}

	@Test
	void testGetCheckingChange() {
		Audit a = new Audit();
		a.setCheckingChange(10);
		double test = a.getCheckingChange();
		assertEquals(10, test);
	}

	@Test
	void testGetOldApproved() {
		Audit a = new Audit();
		a.setOldApproved(true);
		boolean test = a.getOldApproved();
		assertTrue(test);
	}

	@Test
	void testGetNewApproved() {
		Audit a = new Audit();
		a.setNewApproved(true);
		boolean test = a.getNewApproved();
		assertTrue(test);
	}

	@Test
	void testGetOldJoint() {
		Audit a = new Audit();
		a.setOldJoint(true);
		boolean test = a.getOldJoint();
		assertTrue(test);
	}

	@Test
	void testGetNewJoint() {
		Audit a = new Audit();
		a.setNewJoint(true);
		boolean test = a.getNewJoint();
		assertTrue(test);
	}

}
