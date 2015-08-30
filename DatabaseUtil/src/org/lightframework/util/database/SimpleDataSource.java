package org.lightframework.util.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class SimpleDataSource implements DataSource {
	private String drivername = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private Connection connection = null;

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				makeConnection();
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
		} 
		return connection;	
	}

	public Connection getConnection(String username, String password) throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
		
		try {
			makeConnection(username, password);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return connection;
	}

	public void setDriverName(String drivername) throws ClassNotFoundException {
		this.drivername = drivername;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private Connection makeConnection() throws ClassNotFoundException, SQLException {
		if (username != null) {
			return makeConnection(username, password);
		}
		Class.forName(drivername);
		connection = DriverManager.getConnection(url);
		return connection;
	}
	
	private Connection makeConnection(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(drivername);
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
 }
