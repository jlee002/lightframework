package org.lightframework.sample.dao;

import javax.sql.DataSource;

import org.lightframework.context.annotation.Bean;
import org.lightframework.context.annotation.Configuration;
import org.lightframework.util.database.SimpleDataSource;

@Configuration
public class DaoFactory {
	private static DaoFactory daoFactory = new DaoFactory();
	
	@Bean
	public DataSource dataSource() throws ClassNotFoundException {
		SimpleDataSource dataSource = new SimpleDataSource();
		
		dataSource.setDrivername("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl("jdbc:derby:/home/jhlee/workspaces/framework/derby/testdb");
		
		return dataSource;
	}
	
	@Bean
	public UserDao userDao() throws RuntimeException {
		UserDao userDao = new UserDao();
		try {
			userDao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return userDao;
	}
	
	public static DaoFactory getInstance() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		
		return daoFactory;
	}
}
