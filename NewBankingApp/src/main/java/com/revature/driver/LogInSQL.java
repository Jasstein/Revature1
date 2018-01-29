package com.revature.driver;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.banking.Account;
import com.revature.banking.Admin;
import com.revature.banking.Audit;
import com.revature.banking.Customer;
import com.revature.banking.Employee;
import com.revature.data.BankOracle;
import com.revature.util.ConnectionUtil;

public class LogInSQL {
	public static ConnectionUtil cu = ConnectionUtil.getInstance();
	public static Logger log = Logger.getLogger(LogInSQL.class);
	public static BankOracle b = new BankOracle();
	
	public static void main(String[] args) throws IOException {
		LogInSQL.main();
	}
	
	public static void main() throws IOException{
		List<Customer> cList = b.getAllCust(); //put customers into cList
		List<Employee> eList = b.getAllEmp(); //put employees into eList
		List<Admin> adList = b.getAllAdmin(); //put admins into adList
		List<Account> aList = b.getAllAccount(); //put accounts into aList
		List<Audit> dList = b.getAllAudit(); //put audits into dList
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter L to log-in, R to register, E for employees, or A for admins: ");
		String check = scan.nextLine();
		if(check.equals("R")) {//register new customer account
			System.out.println("Make a joint account? Other account must already be set up.");
			System.out.println("Enter Y to make joint account, or N otherwise.");
			String joint = scan.nextLine(); //determine whether to make joint account
			String newUserName = null;
			String newPass = null;
			System.out.println("Enter username: ");
			boolean checking1 = true;
			if(cList.size() == 0) { //no need to check if no customers in data
				newUserName = scan.nextLine();
			}
			else while(checking1) { //make sure usernames don't overlap for simplicity
				newUserName = scan.nextLine();
				for(int i = 0; i < cList.size(); i++) {
					if(newUserName.equals(cList.get(i).getUserName())) {
						System.out.println("User name taken. Please input another:");
						break;
					}
					else if(i == cList.size()-1)
						checking1 = false;
								
				}
			}
			System.out.println("Enter password: ");
			newPass = scan.nextLine();
			
			if(!joint.equals("Y")) {
				boolean checking2 = true;
				int newActNum = 0;
				
				if(cList.size() == 0) { //no need to check if no customers in data
					Random rand = new Random();
					newActNum = 10000000 + rand.nextInt(900000000);
				}
				else while(checking2) { //make sure account numbers don't overlap
					Random rand = new Random();
					newActNum = 10000000 + rand.nextInt(900000000); //generate 9-digit account number
					for(int i = 0; i < aList.size(); i++) {
						if(newActNum == aList.get(i).getAccountNumber()) {
							break;
						}
						else if(i == aList.size()-1)
							checking2 = false;						
					}
				}
				b.addCust(newUserName, newPass, newActNum);
				b.addAccount(newActNum, 0, 0, 0);
				System.out.println("Customer account created.");
				System.out.println("Account number is " + newActNum);
			}
			
			else { //making joint account
				boolean checkingj = false;
				System.out.println("Making a joint account.");
				System.out.println("Enter user name of account joining to: ");
				
				String joinA = scan.nextLine();
				Customer tempC = new Customer();
				for (int i = 0; i < cList.size(); i++) {//find user joining to
					tempC = cList.get(i);
					if(joinA.equals(tempC.getUserName())) {
						checkingj = true;
						break;
					}
				}
				if(!checkingj) {//don't transfer if user can't be found
					System.out.println("No matching user name. Please start over.");
				}
				else {
					System.out.println("Found.");
					System.out.println("Enter original customer's password:");
					String checkPass = scan.nextLine();
					Account tempA = new Account();
					for(int i = 0; i < aList.size(); i++) {
						if(tempC.getAccountNum() == aList.get(i).getAccountNumber()) {
							tempA = aList.get(i);
							break;
						}
					}
					if(tempA.getJoint()) {
						System.out.println("Cannot have a joint account with more than 2 people.");
						System.exit(0);
					}
					if(checkPass.equals(tempC.getPass())) {
						b.addCust(newUserName, newPass, tempC.getAccountNum());
						System.out.println("Your account number is: " + tempC.getAccountNum());
						tempA.setJoint(true);
						b.updateAccount(tempA);
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
			for(int i = 0; i < cList.size(); i++) { //determine if valid customer
				Customer temp = cList.get(i);
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
			
			for(int i = 0; i < aList.size(); i++) { //get customer's account
				Account temp = aList.get(i);
				if(currentCust.getAccountNum() == temp.getAccountNumber())
					currentAccount = temp;
			}
			if(currentAccount.getApproved() == false) {
				System.out.println("Account not approved. Please contact an employee to approve.");
				System.exit(0);
			}
			
			while (true) {
				System.out.println("Enter P to change password, S to check balance, D to deposit, W to withdraw, T to transfer, or E to exit.");
				String nextChar = scan.nextLine(); //customer actions
				if(nextChar.equals("P")) {//change customer's password
					currentCust.changePass();
					b.updateCust(currentCust);
				}
				else if(nextChar.equals("S")) {//check account balance
					currentCust.check_account(currentAccount);
				}
				else if(nextChar.equals("D")) {//deposit inputed amount
					currentCust.deposit(currentAccount);
					b.updateAccount(currentAccount);
				}
				else if(nextChar.equals("W")) {//withdraw inputed amount
					currentCust.withdraw(currentAccount);				
					b.updateAccount(currentAccount);
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
					for (int i = 0; i < cList.size(); i++) {//find user transferring to
						tempC = cList.get(i);
						if(transfer.equals(tempC.getUserName())) {
							checking3 = true;
							break;
						}
					}
					if(!checking3) {//don't transfer if user can't be found
						System.out.println("No matching user name.");
					}
					else {
						for (int i = 0; i < aList.size(); i++) {//get account transferring to
							tempA = aList.get(i);
							if(tempC.getAccountNum() == tempA.getAccountNumber())
								break;
						}
						currentCust.transfer(currentAccount, tempA);
						b.updateAccount(currentAccount);
						b.updateAccount(tempA);
					}
				}
			}
		}
		else if(check.equals("E")) {
			System.out.println("Enter password to access:"); //employees must know password to access employee section
			String validation = scan.nextLine();
			if(!validation.equals("password")) {
				System.out.println("Not allowed to access employee account.");
				System.exit(0);
			}
			System.out.println("Employee, input L to log-in or R to register:");
			String check2 = scan.nextLine();
			if(check2.equals("R")) {
				String newUserName = null;
				String newPass = null;
				System.out.println("Enter username: ");
				boolean checking1 = true;
				if(eList.size() == 0) { //no need to check if no employees in data
					newUserName = scan.nextLine();
				}
				else while(checking1) { //make sure usernames don't overlap for simplicity
					newUserName = scan.nextLine();
					for(int i = 0; i < eList.size(); i++) {
						if(newUserName.equals(eList.get(i).getUserName())) {
							System.out.println("User name taken. Please input another:");
							break;
						}
						else if(i == eList.size()-1)
							checking1 = false;
								
					}
				}
				System.out.println("Enter password: ");
				newPass = scan.nextLine();
				b.addEmp(newUserName, newPass);
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
				for(int i = 0; i < eList.size(); i++) { //determine if valid employee
					Employee temp = eList.get(i);
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
					String nextChar = scan.nextLine(); //employee actions
					if(nextChar.equals("P")) {
						currentEmp.changePass();
						b.updateEmp(currentEmp);
					}
					else if(nextChar.equals("C")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentEmp.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentEmp.getAccountInfo(c.getAccountNum(), aList);
							currentEmp.printCustInfo(c, a);
						}
					}
					else if(nextChar.equals("A")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentEmp.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentEmp.getAccountInfo(c.getAccountNum(), aList);
							System.out.println("Account approved: " + a.getApproved());
							System.out.println("Enter T to approve account or F to deny.");
							String approve = scan.nextLine();
							if(approve.equals("T")) {
								a.setApproved(true);
								System.out.println(c.getUserName() + " approved.");
								b.updateAccount(a);
							}
							else if(approve.equals("F")) {
								a.setApproved(false);
								System.out.println(c.getUserName() + " denied.");
								b.updateAccount(a);
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
				System.out.println("Not a valid input. Exiting");
			}
		}
		else if(check.equals("A")) {
			System.out.println("Enter password to access:"); //admins must know password to access admin section
			String validation = scan.nextLine();
			if(!validation.equals("superpassword")) {
				System.out.println("Not allowed to access admin account.");
				System.exit(0);
			}
			System.out.println("Admin, input L to log-in or R to register:");
			String check2 = scan.nextLine();
			if(check2.equals("R")) {
				String newUserName = null;
				String newPass = null;
				System.out.println("Enter username: ");
				boolean checking1 = true;
				if(adList.size() == 0) { //no need to check if no admins in data
					newUserName = scan.nextLine();
				}
				else while(checking1) { //make sure usernames don't overlap for simplicity
					newUserName = scan.nextLine();
					for(int i = 0; i < adList.size(); i++) {
						if(newUserName.equals(adList.get(i).getUserName())) {
							System.out.println("User name taken. Please input another:");
							break;
						}
						else if(i == adList.size()-1)
							checking1 = false;
								
					}
				}
				System.out.println("Enter password: ");
				newPass = scan.nextLine();
				b.addAdmin(newUserName, newPass);
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
				for(int i = 0; i < adList.size(); i++) { //determine if valid admin
					Admin temp = adList.get(i);
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
					System.out.println("CP to change customer's password, EP to change employee's password, AU to check audit logs, ");
					System.out.println("CA to cancel customer, CE to cancel employee, CD to cancel admin, or E to exit");
					String nextChar = scan.nextLine(); //admin actions
					if(nextChar.equals("P")) {
						currentAd.changePass();
						b.updateAdmin(currentAd);
					}
					else if(nextChar.equals("C")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							currentAd.printCustInfo(c, a);
						}
					}
					else if(nextChar.equals("A")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							System.out.println("Account approved: " + a.getApproved());
							System.out.println("Enter T to approve account or F to deny.");
							String approve = scan.nextLine();
							if(approve.equals("T")) {
								a.setApproved(true);
								System.out.println(c.getUserName() + " approved.");
								b.approveAccount(a);
							}
							else if(approve.equals("F")) {
								a.setApproved(false);
								System.out.println(c.getUserName() + " denied.");
								b.approveAccount(a);
							}
							else
								System.out.println("Not a valid input. Approval remains the same.");
						}
						
					}
					else if(nextChar.equals("D")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							c.deposit(a);
							b.updateAccount(a);
						}
					}
					else if(nextChar.equals("W")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							c.withdraw(a);
							b.updateAccount(a);
						}
					}
					else if(nextChar.equals("T")) {
						System.out.println("Enter customer user name transferring from:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							System.out.println("Checking account balance: " + a.getCheckingAmt());
							boolean checking3 = false;
							System.out.println("Enter user name to transfer money to:");
							String transfer = scan.nextLine();
							Customer tempC = new Customer();
							Account tempA = new Account();
							for (int i = 0; i < cList.size(); i++) {//find user transferring to
								tempC = cList.get(i);
								if(transfer.equals(tempC.getUserName())) {
									checking3 = true;
									break;
								}
							}
							if(!checking3) {//don't transfer if user can't be found
								System.out.println("No matching user name.");
							}
							else {
								for (int i = 0; i < aList.size(); i++) {//get account transferring to
									tempA = aList.get(i);
									if(tempC.getAccountNum() == tempA.getAccountNumber())
										break;
								}
								c.transfer(a, tempA);
								b.updateAccount(a);
								b.updateAccount(tempA);
							}
						}
					}
					else if(nextChar.equals("CP")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							c.changePass();
							b.updateCust(c);
							
						}
					}
					else if(nextChar.equals("EP")) {
						System.out.println("Enter employee user name:");
						String empName = scan.nextLine();
						Employee e = currentAd.getEmployeeInfo(empName, eList);
						if(e == null) {
							System.out.println("Not a valid employee user name.");
						}
						else {
							e.changePass();
							b.updateEmp(e);
							
						}
					}
					else if(nextChar.equals("AU")) {
						for(int i = 0; i < dList.size(); i++) {
							Audit d = dList.get(i);
							System.out.print("AuditId: " + d.getAuditId() + " Account Number: " + d.getAccountNum());
							System.out.print(" Checking Change: " + d.getCheckingChange() + " Old Approved: " + d.getOldApproved());
							System.out.print(" New Approved: " + d.getNewApproved() + " Old Joint: " + d.getOldJoint());
							System.out.println(" New Joint: " + d.getNewJoint());
						}
					}
					else if(nextChar.equals("CA")) {
						System.out.println("Enter customer user name:");
						String custName = scan.nextLine();
						Customer c = currentAd.getCustomerInfo(custName, cList);
						if(c == null) {
							System.out.println("Not a valid customer user name.");
						}
						else {
							Account a = currentAd.getAccountInfo(c.getAccountNum(), aList);
							if(!a.getJoint()) {
								for (Iterator<Customer> i = cList.iterator(); i.hasNext();) { //use iterator to remove customer from data
									Customer value = i.next();
									if (value.getAccountNum() == c.getAccountNum()) {
										i.remove();
									}
									}
								for (Iterator<Account> i = aList.iterator(); i.hasNext();) { //use iterator to remove account from data
									Account value = i.next();
									if (value.getAccountNumber() == a.getAccountNumber())
										i.remove();
									}
								b.deleteAccount(a);
								b.deleteCustomer(c);
								System.out.println("Customer and Account canceled.");
								}
							else {
								for (Iterator<Customer> i = cList.iterator(); i.hasNext();) { //use iterator to remove customer from data
									Customer value = i.next();
									if (value.getUserName().equals(c.getUserName()))
										i.remove();
									}
								a.setJoint(false);
								b.updateAccount(a);
								b.deleteCustomer(c);
								System.out.println("Account canceled. Joint account is no longer joint.");
							}
						}	
					}
					else if(nextChar.equals("CE")) {
						System.out.println("Enter employee user name:");
						String empName = scan.nextLine();
						Employee e = currentAd.getEmployeeInfo(empName, eList);
						if(e == null) {
							System.out.println("Not a valid employee user name.");
						}
						else {
							b.deleteEmp(e);
							System.out.println("Employee cancelled.");
						}
					}
					else if(nextChar.equals("CD")) {
						System.out.println("Enter admin user name:");
						String adName = scan.nextLine();
						if (currentAd.getUserName().equals(adName)) {
							System.out.println("Cannot cancel own account.");
						}
						else {
							Admin a = currentAd.getAdminInfo(adName, adList);
							if(a == null) {
								System.out.println("Not a valid admin user name.");
							}
							else {
								b.deleteAdmin(a);
								System.out.println("Admin cancelled.");
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
			else {
				System.out.println("Not a valid input. Exiting.");
				System.exit(0);
			}
		}
		else
			System.out.println("Not a valid option. Please try again.");
	}

}
