package com.revature.banking;

import java.io.Serializable;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int accountNumber;
	private double checkingAmt;
	private boolean approved;
	public boolean joint;
	
	public void setAccountNumber(int i) {
		this.accountNumber = i;
	}
	
	public void setCheckingAmt(double d) {
		if (d >=0)
			this.checkingAmt = d;
		else
			System.out.println("Invalid number.");
	}
	
	public void setApproved(boolean b) {
		this.approved = b;
	}
	
	public void setJoint(boolean b){
		this.joint = b;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public double getCheckingAmt() {
		return checkingAmt;
	}
	
	public boolean getApproved() {
		return approved;
	}
	
	public boolean getJoint() {
		return joint;
	}

}