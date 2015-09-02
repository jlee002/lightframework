package org.lightframework.sample.test;

import static org.lightframework.test.check.Assert.assertThat;
import static org.lightframework.test.check.Matchers.is;

import java.sql.SQLException;

import org.lightframework.sample.dao.DaoFactory;
import org.lightframework.sample.dao.UserDao;
import org.lightframework.sample.domain.User;
import org.lightframework.test.annotation.Before;
import org.lightframework.test.annotation.Test;
import org.lightframework.test.runner.TestUnit;

public class UserDaoTest {
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() throws ClassNotFoundException {
		dao = DaoFactory.getInstance().userDao();
		dao.setDataSource(DaoFactory.getInstance().dataSource());
		user1 = new User("ch0203_01", "Ch0203_01", "ps0203_01");
		user2 = new User("ch0203_02", "Ch0203_02", "ps0203_02");
		user3 = new User("ch0203_03", "Ch0203_03", "ps0203_03");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}

	/*
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	*/
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TestUnit.main(UserDaoTest.class.getName());
	}
}
