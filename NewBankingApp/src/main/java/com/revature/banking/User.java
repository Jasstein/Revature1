package com.revature.banking;

import java.util.Scanner;

public abstract class User {
	
	public String userName;
	public String password;
	
	public void setUserName(String s) {
		this.userName = s;
	}
	public void setPass(String p) {
		this.password = p;
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return password;
	}
	@SuppressWarnings("resource")
	public void changePass() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter new password: ");
		String newPass = scan.nextLine();
		this.setPass(newPass);
		System.out.println("Password changed.");
	}

}