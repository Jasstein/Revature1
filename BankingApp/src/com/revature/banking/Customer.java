package com.revature.banking;

import java.io.Serializable;

public class Customer extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private int accountNum;
	
	public void setUserName(String s) {
		this.userName = s;
	}
	
	public void setPass(String s) {
		this.password = s;
	}
	
	public void setAccountNum(int i) {
		this.accountNum = i;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPass() {
		return password;
	}
	
	public int getAccountNum() {
		return accountNum;
	}
	
	public boolean isValid(String u, String p) {
		if (this.getUserName().equals(u) && this.getPass().equals(p))
			return true;
		return false;
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
