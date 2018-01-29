package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.banking.Account;
import com.revature.banking.Admin;
import com.revature.banking.Audit;
import com.revature.banking.Customer;
import com.revature.banking.Employee;

class BankOracleTest {
	
	BankOracle b = new BankOracle();

	@Test
	void testAddCust() {
		String s = "Bob";
		String p = "123";
		int i = 789;
		int test = b.addCust(s, p, i);
		assertEquals(0, test);
		
	}

	@Test
	void testGetAllCust() {
		List<Customer> test = b.getAllCust();
		assertFalse(test.isEmpty());
	}

	@Test
	void testAddEmp() {
		String s = "Bob";
		String p = "123";
		int test = b.addEmp(s, p);
		assertEquals(0, test);
	}

	@Test
	void testGetAllEmp() {
		List<Employee> test = b.getAllEmp();
		assertTrue(test.isEmpty());
	}

	@Test
	void testAddAdmin() {
		String s = "Bob";
		String p = "123";
		int test = b.addAdmin(s, p);
		assertEquals(0, test);
	}

	@Test
	void testGetAllAdmin() {
		List<Admin> test = b.getAllAdmin();
		assertTrue(test.isEmpty());
	}

	@Test
	void testAddAccount() {
		int i = 789;
		double d = 10;
		int a = 0;
		int j = 0;
		int test = b.addAccount(i, d, a, j);
		assertEquals(0, test);
	}

	@Test
	void testGetAllAccount() {
		List<Account> test = b.getAllAccount();
		assertFalse(test.isEmpty());
	}

	@Test
	void testUpdateAccount() {
		Account a = new Account();
		boolean test = b.updateAccount(a);
		assertFalse(test);
	}

	@Test
	void testUpdateCust() {
		Customer c = new Customer();
		boolean test = b.updateCust(c);
		assertFalse(test);
	}

	@Test
	void testUpdateEmp() {
		Employee e = new Employee();
		boolean test = b.updateEmp(e);
		assertFalse(test);
	}

	@Test
	void testUpdateAdmin() {
		Admin a = new Admin();
		boolean test = b.updateAdmin(a);
		assertFalse(test);
	}

	@Test
	void testDeleteCustomer() {
		Customer c = new Customer();
		boolean test = b.deleteCustomer(c);
		assertFalse(test);
	}

	@Test
	void testDeleteAccount() {
		Account a = new Account();
		boolean test = b.deleteAccount(a);
		assertFalse(test);
	}

	@Test
	void testDeleteEmp() {
		Employee e = new Employee();
		boolean test = b.deleteEmp(e);
		assertFalse(test);
	}

	@Test
	void testDeleteAdmin() {
		Admin a = new Admin();
		boolean test = b.deleteAdmin(a);
		assertFalse(test);
	}

	@Test
	void testApproveAccount() {
		Account a = new Account();
		boolean test = b.approveAccount(a);
		assertFalse(test);
	}

	@Test
	void testGetAllAudit() {
		List<Audit> test = b.getAllAudit();
		assertTrue(test.isEmpty());
	}

}
