package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public void setUserName(String s) {
		this.userName = s;
	}
	
	public void setPass(String s) {
		this.password = s;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPass() {
		return password;
	}
	
	public boolean isValid(String u, String p) {
		if (this.getUserName().equals(u) && this.getPass().equals(p))
			return true;
		return false;
	}
	
	public Customer getCustomerInfo(String s, ArrayList<Customer> arr) {
		Customer c = new Customer();
		for (int i = 0; i < arr.size(); i++) {
			Customer temp = arr.get(i);
	//		try {
				if(temp.getUserName().equals(s)) {
					c = temp;
					break;
				}
		/*	}
			catch (Exception e){
				return null;
			}*/
			if(i == arr.size() - 1)
				return null;
		}
		return c;
	}
	
	public Employee getEmployeeInfo(String s, ArrayList<Employee> arr) {
		Employee e = new Employee();
		for (int i = 0; i < arr.size(); i++) {
			Employee temp = arr.get(i);
			if(temp.getUserName().equals(s)) {
				e = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return e;
	}
	
	public Account getAccountInfo(int j, ArrayList<Account> arr) {
		Account a = new Account();
		for (int i = 0; i < arr.size(); i++) {
			Account temp = arr.get(i);
			if(temp.getAccountNumber() == j) {
				a = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return a;
	}
	
	public void withdraw(double d, Account a) {
		double s = a.getCheckingAmt();
		if (s < d || d < 0)
			System.out.println("Invalid withdraw amount.");
		else
			a.setCheckingAmt(s - d);
	}
	
	public void deposit(double d, Account a) {
		if (d < 0) {
			System.out.println("Invalid deposit amount.");
		}
		else {
			double s = a.getCheckingAmt();
			a.setCheckingAmt(d + s);
		}
	}
	

}
