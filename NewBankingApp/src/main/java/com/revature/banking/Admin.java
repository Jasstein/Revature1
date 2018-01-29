package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	public Employee getEmployeeInfo(String s, List<Employee> arr) {
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
	
	public Admin getAdminInfo(String s, ArrayList<Admin> arr) {
		Admin a = new Admin();
		for (int i = 0; i < arr.size(); i++) {
			Admin temp = arr.get(i);
			if(temp.getUserName().equals(s)) {
				a = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return a;
	}
	
	public Admin getAdminInfo(String s, List<Admin> arr) {
		Admin a = new Admin();
		for (int i = 0; i < arr.size(); i++) {
			Admin temp = arr.get(i);
			if(temp.getUserName().equals(s)) {
				a = temp;
				break;
			}
			if(i == arr.size() - 1)
				return null;
		}
		return a;
	}

}