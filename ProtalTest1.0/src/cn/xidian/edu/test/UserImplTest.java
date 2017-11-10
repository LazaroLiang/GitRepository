package cn.xidian.edu.test;

import cn.xidian.edu.Dao.User;
import cn.xidian.edu.DaoImpl.UserImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* UserImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 8, 2017</pre> 
* @version 1.0 
*/ 
public class UserImplTest {
    User user;
    ApplicationContext context;
@Before
public void before() throws Exception {
    context=new ClassPathXmlApplicationContext("file:C:\\Users\\I342400\\Documents\\My Received Files\\ProtalTest1.0\\web\\WEB-INF\\applicationContext.xml");
    user=(User) context.getBean("user");
    user.setUser_name("JerryLiang");
    user.setUser_type("admin");
    user.setUser_password("123456");

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addUser(User user) 
* 
*/ 
@Test
public void testAddUser() throws Exception { 
//TODO: Test goes here...
//
    UserImpl userImpl= (UserImpl) context.getBean("userImpl");
    userImpl.addUser(user);
//    UserImpl

}

/** 
* 
* Method: updateUser(User user) 
* 
*/ 
@Test
public void testUpdateUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteUser(int userId) 
* 
*/ 
@Test
public void testDeleteUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findUserById(int userId) 
* 
*/ 
@Test
public void testFindUserById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllUser() 
* 
*/ 
@Test
public void testFindAllUser() throws Exception { 
//TODO: Test goes here... 
} 


} 
