package com.revature.banking;

import java.io.Serializable;
import java.util.Scanner;

public class Customer extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String userName;
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
		return this.userName;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public int getAccountNum() {
		return accountNum;
	}
	
	public boolean isValid(String u, String p) {
		if (getUserName().equals(u) && getPass().equals(p)) {
			return true;
		}
		return false;
	}
	
	public void withdraw(Account a) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter withdraw amount: ");
			double d = scan.nextDouble();
			double s = a.getCheckingAmt();
			if (s < d || d < 0)
				System.out.println("Invalid withdraw amount.");
			else
				a.setCheckingAmt(s - d);
			System.out.println("Current balance is: $" + a.getCheckingAmt());
			System.out.println();
		} catch(Exception e) {
			System.out.println("Not a valid input.");
		}
	}
	
	public void deposit(Account a) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter deposit amount: ");
			double d = scan.nextDouble();
			
			if (d < 0) {
				System.out.println("Invalid deposit amount.");
			}
			else {
				double s = a.getCheckingAmt();
				a.setCheckingAmt(d + s);
			}
			System.out.println("Current balance is: $" + a.getCheckingAmt());
			System.out.println();
		} catch(Exception e) {
			System.out.println("Not a valid input.");
		}
	}
	
	public void transfer(Account a, Account b){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter amount to transfer:");
			double d = scan.nextDouble();
			double s = a.getCheckingAmt();
			if (s < d || d < 0) {
				System.out.println("Invalid withdraw amount.");
				System.out.println("Current balance is: $" + a.getCheckingAmt());
			}
			else {
				a.setCheckingAmt(s - d);
				System.out.println("Current balance is: $" + a.getCheckingAmt());
				System.out.println();					
			
				double t = b.getCheckingAmt();
				b.setCheckingAmt(d + t);
				System.out.println("Their current balance is: $" + b.getCheckingAmt());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Not a valid input.");
		}
	}
	
	public void check_account(Account a) {
		System.out.println("Checking balance is: $" + a.getCheckingAmt());
		System.out.println("Account number is: " + a.getAccountNumber());
		System.out.println("Joint account: " + a.getJoint());
	}

}