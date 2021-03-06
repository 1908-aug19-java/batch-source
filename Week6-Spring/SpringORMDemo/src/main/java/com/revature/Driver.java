package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class Driver {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao = (UserDao) ac.getBean("userDaoImpl");
		
		List<User> users = userDao.getUsers();
		for(User u: users) {
			System.out.println(u);
		}

	}

}
