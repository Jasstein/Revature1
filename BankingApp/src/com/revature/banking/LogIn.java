package com.revature.banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//run this class
//get to employee with password
//get to admin with superpassword

public class LogIn {
	
//	static final Logger logger = LogManager.getLogger("LogIn");
	
	public static void main(String[] args) throws IOException {
		LogIn.main();
	}
	public static void main () throws IOException{
		
	//	logger.info("Test");
		
		String filename = "CustomerData.txt";
		String filename2 = "AccountData.txt";
		String filename3 = "EmployeeData.txt";
		String filename4 = "AdminData.txt";
		ArrayList<Customer> arr = getCustomerList();
		ArrayList<Account> arr2 = getAccountList();
		ArrayList<Employee> arr3 = getEmployeeList();
		ArrayList<Admin> arr4 = getAdminList();
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter L to log-in, R to register, E for employees, or A for admins: ");
		String check = scan.nextLine();
		if (check.equals("R")){ //register new account
			System.out.println("Make a joint account? Other account must already be set up.");
			System.out.println("Enter Y to make joint account, or N otherwise.");
			String joint = scan.nextLine();
			Customer newCust = new Customer();
			String newUserName = null;
			System.out.println("Enter username: "); //set up user name
			boolean checking1 = true;
			if(arr.size() == 0) { //no need to check if no customers in data
				newUserName = scan.nextLine();
			}
			else while(checking1) { //make sure usernames don't overlap for simplicity
				newUserName = scan.nextLine();
				for(int i = 0; i < arr.size(); i++) {
					if(newUserName.equals(arr.get(i).getUserName())) {
						System.out.println("User name taken. Please input another:");
						break;
					}
					else if(i == arr.size()-1)
						checking1 = false;
							
				}
			}
			newCust.setUserName(newUserName);
			
			System.out.println("Enter password: "); //set up password
			String newPass = scan.nextLine();
			newCust.setPass(newPass);
			
			if(!joint.equals("Y")) {			
				boolean checking2 = true;
				int newActNum = 0;
				
				if(arr.size() == 0) { //no need to check if no customers in data
					Random rand = new Random();
					newActNum = 10000000 + rand.nextInt(900000000);
				}
				else while(checking2) { //make sure account numbers don't overlap
					Random rand = new Random();
					newActNum = 10000000 + rand.nextInt(900000000); //generate 9-digit account number
					for(int i = 0; i < arr.size(); i++) {
						if(newActNum == arr.get(i).getAccountNum()) {
							break;
						}
						else if(i == arr.size()-1)
							checking2 = false;						
					}
				}
				
				newCust.setAccountNum(newActNum);
				System.out.println("Your account number is: " + newCust.getAccountNum());
				
				writeData(filename, newCust);
				
				Account a = new Account(); //set up account
				a.setAccountNumber(newCust.getAccountNum());
				a.setCheckingAmt(0);
				a.setApproved(false); //default approval false
				a.setJoint(false); //default joint false
				writeData(filename2, a);
			}
			else {
				boolean checkingj = false;
				System.out.println("Making a joint account.");
				System.out.println("Enter user name of account joining to: ");
				
				String joinA = scan.nextLine();
				Customer tempC = new Customer();
				for (int i = 0; i < arr.size(); i++) {//find user joining to
					tempC = arr.get(i);
					if(joinA.equals(tempC.getUserName())) {
						checkingj = true;
						break;
					}
				}
				if(!checkingj) {//don't transfer if user can't be found
					System.out.println("No matching user name. Please try again.");
				}
				else {
					System.out.println("Found.");
					System.out.println("Enter original customer's password:");
					String checkPass = scan.nextLine();
					Account tempA = new Account();
					for(int i = 0; i < arr2.size(); i++) {
						if(tempC.getAccountNum() == arr2.get(i).getAccountNumber()) {
							tempA = arr2.get(i);
							break;
						}
					}
					if(tempA.getJoint()) {
						System.out.println("Cannot have a joint account with more than 2 people.");
						System.exit(0);
					}
					if(checkPass.equals(tempC.getPass())) {
						newCust.setAccountNum(tempC.getAccountNum());
						writeData(filename, newCust);
						System.out.println("Your account number is: " + newCust.getAccountNum());
						tempA.setJoint(true);
						changeAccount(filename2, tempA, arr2);
					}
					else {
						System.out.println("Please get original customer's password to create joint account.");
						System.exit(0);
					}
				}
			}
			
		}
		else if(check.equals("L")) {
			System.out.println("Enter username: ");
			String userName = scan.nextLine();
			System.out.println("Enter password: ");
			String userPass = scan.nextLine();
			Customer currentCust = new Customer();
			Account currentAccount = new Account();
			currentCust.setUserName(userName);
			currentCust.setPass(userPass);
			
			boolean valid = false;
			for(int i = 0; i < arr.size(); i++) { //determine if valid customer
				Customer temp = arr.get(i);
				if(currentCust.isValid(temp.getUserName(), temp.getPass())) {
					System.out.println("Logged in.");
					currentCust = temp;
					valid = true;
					break;
				}
			}
			
			if(!valid) { //if not valid login, exit
				System.out.println("User name and password do not match. Please try again.");
				System.exit(0);
			}
			
			for(int i = 0; i < arr2.size(); i++) { //get customer's account
				Account temp = arr2.get(i);
				if(currentCust.getAccountNum() == temp.getAccountNumber())
					currentAccount = temp;
			}
			if(currentAccount.getApproved() == false) {
				System.out.println("Account not approved. Please contact an employee to approve.");
				System.exit(0);
			}
			
			while (true) {
				System.out.println("Enter P to change password, S to check balance, D to deposit, W to withdraw, T to transfer, or E to exit.");
				String nextChar = scan.nextLine();
				if(nextChar.equals("P")) {//change customer's password
					System.out.println("Enter new password: ");
					String newPass = scan.nextLine();
					currentCust.setPass(newPass);
					changeCustomer(filename, currentCust, arr);
				}
				else if(nextChar.equals("S")) {//check account balance
					System.out.println("Checking balance is: $" + currentAccount.getCheckingAmt());
					System.out.println("Account number is: " + currentAccount.getAccountNumber());
					System.out.println("Joint account: " + currentAccount.getJoint());
					System.out.println();
				}
				else if(nextChar.equals("D")) {//deposit inputed amount
					System.out.println("Enter deposit amount: ");
					double d = scan.nextDouble();
					currentCust.deposit(d, currentAccount);
					System.out.println("Current balance is: $" + currentAccount.getCheckingAmt());
					System.out.println();
					changeAccount(filename2, currentAccount, arr2);
					scan.nextLine(); //bug fix
				}
				else if(nextChar.equals("W")) {//withdraw inputed amount
					System.out.println("Enter withdraw amount: ");
					double d = scan.nextDouble();
					currentCust.withdraw(d, currentAccount);
					System.out.println("Current balance is: $" + currentAccount.getCheckingAmt());
					System.out.println();					
					changeAccount(filename2, currentAccount, arr2);
					scan.nextLine(); //bug fix
				}
				else if(nextChar.equals("E")) {//finish
					System.out.println("Exiting.");
					System.exit(0);
				}
				else if(nextChar.equals("T")) {//transfer money to another account
					boolean checking3 = false;
					System.out.println("Enter user name to transfer money to:");
					String transfer = scan.nextLine();
					Customer tempC = new Customer();
					Account tempA = new Account();
					for (int i = 0; i < arr.size(); i++) {//find user transferring to
						tempC = arr.get(i);
						if(transfer.equals(tempC.getUserName())) {
							checking3 = true;
							break;
						}
					}
					if(!checking3) {//don't transfer if user can't be found
						System.out.println("No matching user name.");
					}
					else {
						for (int i = 0; i < arr2.size(); i++) {//get account transferring to
							tempA = arr2.get(i);
							if(tempC.getAccountNum() == tempA.getAccountNumber())
								break;
						}
						
						System.out.println("Enter amount to transfer:");
						double d = scan.nextDouble();
						currentCust.withdraw(d, currentAccount);
						System.out.println("Your current balance is: $" + currentAccount.getCheckingAmt());
						System.out.println();					
						changeAccount(filename2, currentAccount, arr2);
						scan.nextLine(); //bug fix
						
						tempC.deposit(d, tempA);
						System.out.println("Their current balance is: $" + tempA.getCheckingAmt());
						System.out.println();
						changeAccount(filename2, tempA, arr2);
					}
				}
				else {
					System.out.println("Not a valid input.");
				}
			}
		}
		else if(check.equals("E")) { //employees
			System.out.println("Enter password to access:"); //employees must know password to access employee section
			String validation = scan.nextLine();
			if(!validation.equals("password")) {
				System.out.println("Not allowed to access employee account.");
				System.exit(0);
			}
			System.out.println("Employee, input L to log-in or R to register:");
			String check2 = scan.nextLine();
			if(check2.equals("R")) {
				Employee newEmp = new Employee();
				String newUserName = null;
				System.out.println("Enter username: "); //set up user name
				boolean checking1 = true;
				if(arr3.size() == 0) { //no need to check if no employees in data
					newUserName = scan.nextLine();
				}
				else while(checking1) { //make sure usernames don't overlap for simplicity
					newUserName = scan.nextLine();
					for(int i = 0; i < arr3.size(); i++) {
						if(newUserName.equals(arr3.get(i).getUserName())) {
							System.out.println("User name taken. Please input another:");
							break;
						}
						else if(i == arr3.size()-1)
							checking1 = false;
								
					}
				}
				newEmp.setUserName(newUserName);
				
				System.out.println("Enter password: "); //set up password
				String newPass = scan.nextLine();
				newEmp.setPass(newPass);
				writeData(filename3, newEmp);
				System.out.println("Employee account created.");
			}
			else if(check2.equals("L")) {
				System.out.println("Enter username: ");
				String userName = scan.nextLine();
				System.out.println("Enter password: ");
				String userPass = scan.nextLine();
				Employee currentEmp = new Employee();
				currentEmp.setUserName(userName);
				currentEmp.setPass(userPass);
				
				boolean valid = false;
				for(int i = 0; i < arr.size(); i++) { //determine if valid employee
					Employee temp = arr3.get(i);
					if(currentEmp.isValid(temp.getUserName(), temp.getPass())) {
						System.out.println("Logged in.");
						currentEmp = temp;
						valid = true;
						break;
					}
				}
				
				if(!valid) { //if not valid login, exit
					System.out.println("User name and password do not match. Please try again.");
					System.exit(0);
				}
				
				while(true) {
					System.out.println("Enter P to change password, C to see customer info, A to approve accounts, or E to exit");
					String nextChar = scan.nextLine();
					if(nextChar.equals("P")) {
						System.out.println("Enter new password: ");
						String newPass = scan.nextLine();
						currentEmp.setPass(newPass);
						changeEmployee(filename3, currentEmp, arr3);
					}
					else if(nextChar.equals("C")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentEmp.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentEmp.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Customer username: " + c.getUserName());
							System.out.println("Custoemr password: " + c.getPass());
							System.out.println("Account number: " + a.getAccountNumber());
							System.out.println("Account approved:" + a.getApproved());
							System.out.println("Account checking balance: " + a.getCheckingAmt());
							System.out.println("Joint account: " + a.getJoint());
						}
					}
					else if(nextChar.equals("A")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentEmp.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentEmp.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Account approved: " + a.getApproved());
							System.out.println("Enter T to approve account or F to deny.");
							String approve = scan.nextLine();
							if(approve.equals("T")) {
								a.setApproved(true);
								System.out.println(c.getUserName() + " approved.");
								changeAccount(filename2, a, arr2);
							}
							else if(approve.equals("F")) {
								a.setApproved(false);
								System.out.println(c.getUserName() + " denied.");
								changeAccount(filename2, a, arr2);
							}
							else
								System.out.println("Not a valid input. Approval remains the same.");
						}
						
					}
					else if(nextChar.equals("E")) {
						System.out.println("Exiting");
						System.exit(0);
					}
					else
						System.out.println("Not a valid input.");
				}
				
			}
			else {
				System.out.println("Not a valid option. Exiting.");
				System.exit(0);
			}
		}
		else if(check.equals("A")){
			System.out.println("Enter password to access:"); //admins must know password to access admin section
			String validation = scan.nextLine();
			if(!validation.equals("superpassword")) {
				System.out.println("Not allowed to access admin account.");
				System.exit(0);
			}
			System.out.println("Admin, input L to log-in or R to register:");
			String check2 = scan.nextLine();
			if(check2.equals("R")) {
				Admin newAd = new Admin();
				String newUserName = null;
				System.out.println("Enter username: "); //set up user name
				boolean checking1 = true;
				if(arr4.size() == 0) { //no need to check if no admins in data
					newUserName = scan.nextLine();
				}
				else while(checking1) { //make sure usernames don't overlap for simplicity
					newUserName = scan.nextLine();
					for(int i = 0; i < arr4.size(); i++) {
						if(newUserName.equals(arr4.get(i).getUserName())) {
							System.out.println("User name taken. Please input another:");
							break;
						}
						else if(i == arr4.size()-1)
							checking1 = false;
								
					}
				}
				newAd.setUserName(newUserName);
				
				System.out.println("Enter password: "); //set up password
				String newPass = scan.nextLine();
				newAd.setPass(newPass);
				writeData(filename4, newAd);
				System.out.println("Admin account created.");
			}
			else if(check2.equals("L")) {
				System.out.println("Enter username: ");
				String userName = scan.nextLine();
				System.out.println("Enter password: ");
				String userPass = scan.nextLine();
				Admin currentAd = new Admin();
				currentAd.setUserName(userName);
				currentAd.setPass(userPass);
				
				boolean valid = false;
				for(int i = 0; i < arr.size(); i++) { //determine if valid admin
					Admin temp = arr4.get(i);
					if(currentAd.isValid(temp.getUserName(), temp.getPass())) {
						System.out.println("Logged in.");
						currentAd = temp;
						valid = true;
						break;
					}
				}
				
				if(!valid) { //if not valid login, exit
					System.out.println("User name and password do not match. Please try again.");
					System.exit(0);
				}
				
				while(true) {
					System.out.println("Enter P to change password, C to see customer info, A to approve accounts, D to deposit, W to withdraw, T to transfer,");
					System.out.println("CP to change customer's password, EP to change employee's password, CA to cancel account, or E to exit");
					String nextChar = scan.nextLine();
					if(nextChar.equals("P")) {
						System.out.println("Enter new password: ");
						String newPass = scan.nextLine();
						currentAd.setPass(newPass);
						changeAdmin(filename3, currentAd, arr4);
					}
					else if(nextChar.equals("C")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Customer username: " + c.getUserName());
							System.out.println("Custoemr password: " + c.getPass());
							System.out.println("Account number: " + a.getAccountNumber());
							System.out.println("Account approved:" + a.getApproved());
							System.out.println("Account checking balance: " + a.getCheckingAmt());
							System.out.println("Joint account: " + a.getJoint());
						}
					}
					else if(nextChar.equals("A")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Account approved: " + a.getApproved());
							System.out.println("Enter T to approve account or F to deny.");
							String approve = scan.nextLine();
							if(approve.equals("T")) {
								a.setApproved(true);
								System.out.println(c.getUserName() + " approved.");
								changeAccount(filename2, a, arr2);
							}
							else if(approve.equals("F")) {
								a.setApproved(false);
								System.out.println(c.getUserName() + " denied.");
								changeAccount(filename2, a, arr2);
							}
							else
								System.out.println("Not a valid input. Approval remains the same.");
						}
						
					}
					else if(nextChar.equals("D")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Checking account balance: " + a.getCheckingAmt());
							System.out.println("Enter deposit amount:");
							double d = scan.nextDouble();
							currentAd.deposit(d, a);
							System.out.println("Checking account balance: " + a.getCheckingAmt());
							changeAccount(filename2, a, arr2);
							scan.nextLine();
						}
					}
					else if(nextChar.equals("W")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Checking account balance: " + a.getCheckingAmt());
							System.out.println("Enter withdraw ammount: ");
							double d = scan.nextDouble();
							currentAd.withdraw(d, a);
							System.out.println("Current balance is: $" + a.getCheckingAmt());
							System.out.println();					
							changeAccount(filename2, a, arr2);
							scan.nextLine(); //bug fix
						}
					}
					else if(nextChar.equals("T")) {
						System.out.println("Enter customer user name transferring from:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							System.out.println("Checking account balance: " + a.getCheckingAmt());
							boolean checking3 = false;
							System.out.println("Enter user name to transfer money to:");
							String transfer = scan.nextLine();
							Customer tempC = new Customer();
							Account tempA = new Account();
							for (int i = 0; i < arr.size(); i++) {//find user transferring to
								tempC = arr.get(i);
								if(transfer.equals(tempC.getUserName())) {
									checking3 = true;
									break;
								}
							}
							if(!checking3) {//don't transfer if user can't be found
								System.out.println("No matching user name.");
							}
							else {
								for (int i = 0; i < arr2.size(); i++) {//get account transferring to
									tempA = arr2.get(i);
									if(tempC.getAccountNum() == tempA.getAccountNumber())
										break;
								}
								
								System.out.println("Enter amount to transfer:");
								double d = scan.nextDouble();
								currentAd.withdraw(d, a);
								System.out.println(c.getUserName() + "'s current balance is: $" + a.getCheckingAmt());
								System.out.println();					
								changeAccount(filename2, a, arr2);
								scan.nextLine(); //bug fix
								
								currentAd.deposit(d, tempA);
								System.out.println(tempC.getUserName() + "'s current balance is: $" + tempA.getCheckingAmt());
								System.out.println();
								changeAccount(filename2, tempA, arr2);
							}
						}
					}
					else if(nextChar.equals("CP")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							System.out.println("Enter new password: ");
							String newPass = scan.nextLine();
							c.setPass(newPass);
							changeCustomer(filename, c, arr);
							
						}
					}
					else if(nextChar.equals("EP")) {
						System.out.println("Enter employee user name:");
						String empName = scan.nextLine();
						Employee e = currentAd.getEmployeeInfo(empName, arr3);
						if(e == null) {
							System.out.println("Not a valid employee user name.");
						}
						else {
							System.out.println("Enter new password: ");
							String newPass = scan.nextLine();
							e.setPass(newPass);
							changeEmployee(filename3, e, arr3);
							
						}
					}
					else if(nextChar.equals("CA")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, arr);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), arr2);
							if(!a.getJoint()) {
								for (Iterator<Customer> i = arr.iterator(); i.hasNext();) { //use iterator to remove customer from data
									Customer value = i.next();
									if (value.getAccountNum() == c.getAccountNum()) {
										i.remove();
									}
									}
								for (Iterator<Account> i = arr2.iterator(); i.hasNext();) { //use iterator to remove account from data
									Account value = i.next();
									if (value.getAccountNumber() == a.getAccountNumber())
										i.remove();
									}
								new FileOutputStream(filename).close();
								new FileOutputStream(filename2).close();
								for (int i = 0; i < arr.size(); i++) {
									writeData(filename, arr.get(i));
								}
								for (int i = 0; i < arr2.size(); i++) {
									writeData(filename2, arr2.get(i));
								}
								System.out.println("Customer and Account canceled.");
								}
							else {
								for (Iterator<Customer> i = arr.iterator(); i.hasNext();) { //use iterator to remove customer from data
									Customer value = i.next();
									if (value.getUserName().equals(c.getUserName()))
										i.remove();
									}
								new FileOutputStream(filename).close();
								for (int i = 0; i < arr.size(); i++) {
									writeData(filename, arr.get(i));
								}
								a.setJoint(false);
								changeAccount(filename2, a, arr2);
								System.out.println("Account canceled. Joint account is no longer joint.");
								arr = getCustomerList();
							}
						}
							
					}
					else if(nextChar.equals("E")) {
						System.out.println("Exiting");
						System.exit(0);
					}
					else
						System.out.println("Not a valid input.");
				}
			}
		}
		else
			System.out.println("Not a valid option. Please try again.");
	}
	
	public static ArrayList<Customer> getCustomerList(){ //read customer data from CustomerData.txt
		String filename = "CustomerData.txt"; //customer data file
		ArrayList<Customer> arr = new ArrayList<Customer>();
		try {
			FileInputStream file1 = new FileInputStream(filename);
			
			
		    try {
		        while (true) {
		        	ObjectInputStream in = new ObjectInputStream(file1);
		            arr.add((Customer)in.readObject());
		        }
		    } catch (Exception ex) {
		      }
		    file1.close();
		} catch (Exception ex) {
		    System.out.println("Exception: " + ex);
		    System.out.println("Creating " + filename);
		    try {
		    	FileOutputStream file = new FileOutputStream(filename,true);
		    	ObjectOutputStream out = new ObjectOutputStream(file);
		    	
		    	file.close();
		    	out.close();
		    }
		    catch(Exception ex2) {
		    	System.out.println("Exception: " + ex2);
		    }
		  }
		return arr;
	}
	
	public static ArrayList<Account> getAccountList(){ //read account data from AccountData.txt
		String filename = "AccountData.txt"; //account data file
		ArrayList<Account> arr = new ArrayList<Account>();
		try {
			FileInputStream file1 = new FileInputStream(filename);
			
			
		    try {
		        while (true) {
		        	ObjectInputStream in = new ObjectInputStream(file1);
		            arr.add((Account)in.readObject());
		        }
		    } catch (Exception ex) {
		      }
		    file1.close();
		} catch (Exception ex) {
		    System.out.println("Exception: " + ex);
		    System.out.println("Creating " + filename);
		    try {
		    	FileOutputStream file = new FileOutputStream(filename,true);
		    	ObjectOutputStream out = new ObjectOutputStream(file);
		    	
		    	file.close();
		    	out.close();
		    }
		    catch(Exception ex2) {
		    	System.out.println("Exception: " + ex2);
		    }
		  }
		return arr;
	}
	
	public static ArrayList<Employee> getEmployeeList(){ //read employee data from EmployeeData.txt
		String filename = "EmployeeData.txt"; //employee data file
		ArrayList<Employee> arr = new ArrayList<Employee>();
		try {
			FileInputStream file1 = new FileInputStream(filename);
			
			
		    try {
		        while (true) {
		        	ObjectInputStream in = new ObjectInputStream(file1);
		            arr.add((Employee)in.readObject());
		        }
		    } catch (Exception ex) {
		      }
		    file1.close();
		} catch (Exception ex) {
		    System.out.println("Exception: " + ex);
		    System.out.println("Creating " + filename);
		    try {
		    	FileOutputStream file = new FileOutputStream(filename,true);
		    	ObjectOutputStream out = new ObjectOutputStream(file);
		    	
		    	file.close();
		    	out.close();
		    }
		    catch(Exception ex2) {
		    	System.out.println("Exception: " + ex2);
		    }
		  }
		return arr;
	}
	
	public static ArrayList<Admin> getAdminList(){ //read admin data from AdminData.txt
		String filename = "AdminData.txt"; //admin data file
		ArrayList<Admin> arr = new ArrayList<Admin>();
		try {
			FileInputStream file1 = new FileInputStream(filename);
			
			
		    try {
		        while (true) {
		        	ObjectInputStream in = new ObjectInputStream(file1);
		            arr.add((Admin)in.readObject());
		        }
		    } catch (Exception ex) {
		      }
		    file1.close();
		} catch (Exception ex) {
		    System.out.println("Exception: " + ex);
		    System.out.println("Creating " + filename);
		    try {
		    	FileOutputStream file = new FileOutputStream(filename,true);
		    	ObjectOutputStream out = new ObjectOutputStream(file);
		    	
		    	file.close();
		    	out.close();
		    }
		    catch(Exception ex2) {
		    	System.out.println("Exception: " + ex2);
		    }
		  }
		return arr;
	}

	private static void emptyData(String filename) throws IOException { //empty file
		FileOutputStream file = null;
	    try {
	      file = new FileOutputStream(filename);
	    } catch(Exception ex) {
	    }
	    finally {
	      if (file != null) {
	        file.close();
	      }
	    }
	  }
	
	public static void writeData(String filename, Object o) { //write account data to text file
		try { //serialize account data
			FileOutputStream file = new FileOutputStream(filename,true);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(o);
			
			out.close();
			file.close();
		}
		catch(IOException ex) {
			System.out.println("Exception: " + ex);
		}
	}
	
	public static void changeAccount(String filename, Account a, ArrayList<Account> arr) throws IOException {
		for(int i = 0; i < arr.size(); i++) {
			if(a.getAccountNumber() == arr.get(i).getAccountNumber())
				arr.set(i, a);
		}
		emptyData(filename);
		for(int i = 0; i < arr.size(); i++) {
			writeData(filename, arr.get(i));
		}
	}
	
	public static void changeCustomer(String filename, Customer c, ArrayList<Customer> arr) throws IOException {
		for(int i = 0; i < arr.size(); i++) {
			if(c.getAccountNum() == arr.get(i).getAccountNum())
				arr.set(i, c);
		}
		emptyData(filename);
		for(int i = 0; i < arr.size(); i++) {
			writeData(filename, arr.get(i));
		}
	}
	
	public static void changeEmployee(String filename, Employee e, ArrayList<Employee> arr) throws IOException {
		for(int i = 0; i < arr.size(); i++) {
			if(e.getUserName() == arr.get(i).getUserName())
				arr.set(i, e);
		}
		emptyData(filename);
		for(int i = 0; i < arr.size(); i++) {
			writeData(filename, arr.get(i));
		}
	}
	
	public static void changeAdmin(String filename, Admin a, ArrayList<Admin> arr) throws IOException {
		for(int i = 0; i < arr.size(); i++) {
			if(a.getUserName() == arr.get(i).getUserName())
				arr.set(i, a);
		}
		emptyData(filename);
		for(int i = 0; i < arr.size(); i++) {
			writeData(filename, arr.get(i));
		}
	}

}