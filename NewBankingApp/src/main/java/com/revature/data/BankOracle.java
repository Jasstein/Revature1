package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.Account;
import com.revature.banking.Admin;
import com.revature.banking.Audit;
import com.revature.banking.Customer;
import com.revature.banking.Employee;
import com.revature.util.ConnectionUtil;

public class BankOracle {
	
	private static Logger log = Logger.getLogger(BankOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	
	public int addCust(String s, String p, int i) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into customer (username, pass, account_num)"
					+ " values (?,?,?)";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, s);
			prep.setString(2, p);
			prep.setInt(3, i);
			if(prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return 0;
			}
			conn.rollback();
			return 1;
		}catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return 1;
	}
	
	public List<Customer> getAllCust() {
		List<Customer> cList = new ArrayList<Customer>();
		try(Connection conn = cu.getConnection()) {
			String sql = "Select username, pass, account_num"
					+ " from customer";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setUserName(rs.getString("username"));
				c.setPass(rs.getString("pass"));
				c.setAccountNum(rs.getInt("account_num"));
				cList.add(c);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return cList;
	}
	
	public int addEmp(String s, String p) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into employee (username, pass)"
					+ " values (?,?)";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, s);
			prep.setString(2, p);
			if(prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return 0;
			}
			conn.rollback();
			return 1;
		}catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return 1;
	}
	
	public List<Employee> getAllEmp() {
		List<Employee> eList = new ArrayList<Employee>();
		try(Connection conn = cu.getConnection()) {
			String sql = "Select username, pass"
					+ " from employee";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setUserName(rs.getString("username"));
				e.setPass(rs.getString("pass"));
				eList.add(e);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return eList;
	}
	
	public int addAdmin(String s, String p) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into admin (username, pass)"
					+ " values (?,?)";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, s);
			prep.setString(2, p);
			if(prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return 0;
			}
			conn.rollback();
			return 1;
		}catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return 1;
	}
	
	public List<Admin> getAllAdmin() {
		List<Admin> adList = new ArrayList<Admin>();
		try(Connection conn = cu.getConnection()) {
			String sql = "Select username, pass"
					+ " from admin";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Admin ad = new Admin();
				ad.setUserName(rs.getString("username"));
				ad.setPass(rs.getString("pass"));
				adList.add(ad);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return adList;
	}
	
	public int addAccount(int i, double d, int a, int j) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into account (account_num, checking_amt, approved, joint)"
					+ " values (?,?,?,?)";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, i);
			prep.setDouble(2, d);
			prep.setInt(3, a);
			prep.setInt(4, j);
			if(prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return 0;
			}
			conn.rollback();
			return 1;
		}catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return 1;
	}
	
	public List<Account> getAllAccount() {
		List<Account> aList = new ArrayList<Account>();
		try(Connection conn = cu.getConnection()) {
			String sql = "Select account_num, checking_amt, approved, joint"
					+ " from account";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Account a = new Account();
				a.setAccountNumber(rs.getInt("account_num"));
				a.setCheckingAmt(rs.getDouble("checking_amt"));
				a.setApproved(rs.getBoolean("approved"));
				a.setJoint(rs.getBoolean("joint"));
				aList.add(a);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return aList;
	}
	
	public boolean updateAccount(Account a) {
		try {
			Connection conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "Update account"+ 
					" set checking_amt = ?, approved = ?, joint =?"
					+ " where account_num=?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setDouble(1, a.getCheckingAmt());
			prep.setBoolean(2, a.getApproved());
			prep.setBoolean(3, a.getJoint());
			prep.setInt(4, a.getAccountNumber());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return true;
	}
	
	public boolean updateCust(Customer c) {
		try {
			Connection conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "Update customer"+ 
					" set account_num = ?, pass = ?"
					+ " where username=?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, c.getAccountNum());
			prep.setString(2, c.getPass());
			prep.setString(3, c.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return true;
	}
	
	public boolean updateEmp(Employee e) {
		try {
			Connection conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "Update employee"+ 
					" set pass = ?"
					+ " where username=?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, e.getPass());
			prep.setString(2, e.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;

		} catch (SQLException e1) {
			log.error(e1.getMessage());
		}
		return true;
	}
	
	public boolean updateAdmin(Admin a) {
		try {
			Connection conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "Update admin"+ 
					" set pass = ?"
					+ " where username=?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, a.getPass());
			prep.setString(2, a.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;

		} catch (SQLException e1) {
			log.error(e1.getMessage());
		}
		return true;
	}
	
	public boolean deleteCustomer(Customer c) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "delete from customer where username = ?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, c.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return false;
	}
	
	public boolean deleteAccount(Account a) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "delete from account where account_num = ?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, a.getAccountNumber());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return false;
	}
	
	public boolean deleteEmp(Employee e) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "delete from employee where username = ?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, e.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;
		} catch (SQLException e1) {
			log.error(e1.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e2) {
				log.error(e2.getMessage());
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
		}
		return false;
	}
	
	public boolean deleteAdmin(Admin a) {
		Connection conn = null;
		try {
			conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "delete from admin where username = ?";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, a.getUserName());
			if (prep.executeUpdate() == 1) {
				log.trace("Success!");
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage());
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return false;
	}
	
	public boolean approveAccount(Account a) {
		
		try {
			Connection conn = cu.getConnection();
			String sql = "call accountapproval(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setBoolean(1, a.getApproved());
			cs.setInt(2, a.getAccountNumber());
			if(cs.execute()) {
				System.out.println("test");
				log.trace("Result set how?");
				return true;
			} else {
				log.trace("Approval confirmed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public List<Audit> getAllAudit() {
		List<Audit> aList = new ArrayList<Audit>();
		try(Connection conn = cu.getConnection()) {
			String sql = "Select auditing, account_num, checking_change, "
					+ "old_approved, new_approved, old_joint, new_joint"
					+ " from auditing";
			log.trace(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Audit a = new Audit();
				a.setAuditId(rs.getInt("auditing"));
				a.setAccountNum(rs.getInt("account_num"));
				a.setCheckingChange(rs.getDouble("checking_change"));
				a.setOldApproved(rs.getBoolean("old_approved"));
				a.setNewApproved(rs.getBoolean("new_approved"));
				a.setOldJoint(rs.getBoolean("old_joint"));
				a.setNewJoint(rs.getBoolean("new_joint"));
				aList.add(a);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return aList;
	}

}
