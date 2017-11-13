package test.cn.xidian.edu.Service; 

import cn.xidian.edu.Dao.User;
import cn.xidian.edu.Service.UserService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* UserService Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 13, 2017</pre> 
* @version 1.0 
*/ 
public class UserServiceTest {
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
* Method: setUserImpl(UserImpl userImpl) 
* 
*/ 
@Test
public void testSetUserImpl() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: testTransaction(User user) 
* 
*/ 
@Test
public void testTestTransaction() throws Exception { 
//TODO: Test goes here...
    UserService userService=(UserService) context.getBean("userService");
    userService.testTransaction(user);
} 


} 
