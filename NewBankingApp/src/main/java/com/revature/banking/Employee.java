package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee extends User implements Serializable{

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
			if(temp.getUserName().equals(s)) {
				c = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return c;
	}
	
	public Customer getCustomerInfo(String s, List<Customer> arr) {
		Customer c = new Customer();
		for (int i = 0; i < arr.size(); i++) {
			Customer temp = arr.get(i);
			if(temp.getUserName().equals(s)) {
				c = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return c;
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
	
	public Account getAccountInfo(int j, List<Account> arr) {
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
	
	public void printCustInfo(Customer c, Account a) {
		System.out.println("Customer username: " + c.getUserName());
		System.out.println("Customer password: " + c.getPass());
		System.out.println("Account number: " + a.getAccountNumber());
		System.out.println("Account approved:" + a.getApproved());
		System.out.println("Account checking balance: " + a.getCheckingAmt());
		System.out.println("Joint account: " + a.getJoint());
	}

}