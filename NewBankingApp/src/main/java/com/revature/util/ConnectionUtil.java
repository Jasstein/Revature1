package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop = new Properties();
	
	private ConnectionUtil() {
		super();
		try {
			prop.load(new FileReader("src/main/resources/database.properties"));;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getInstance() {
		if(cu==null)
			cu = new ConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
		conn = DriverManager.getConnection(prop.getProperty("url"),
				prop.getProperty("user"),
				prop.getProperty("pass"));
		}
		catch(Exception e){
			
		}
		return conn;
	}

}