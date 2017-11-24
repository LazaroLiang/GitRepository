package test.cn.xidian.edu.Dao.DaoImpl; 

import cn.xidian.edu.Bean.User;
import cn.xidian.edu.Dao.DaoImpl.UserImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/** 
* UserImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 17, 2017</pre> 
* @version 1.0 
*/ 
public class UserImplTest {
    User user;
    ApplicationContext context;
    UserImpl userImpl;
@Before
public void before() throws Exception {
    context=new ClassPathXmlApplicationContext("file:C:\\Users\\I342400\\Documents\\GitRepository\\ProtalTest1.0\\web\\WEB-INF\\applicationContext.xml");

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
    user=(User) context.getBean("user");
    user.setUser_name("TomLiu");
    user.setUser_type("putong");
    user.setUser_password("123456");
    userImpl=(UserImpl)context.getBean("userImpl");
    userImpl.addUser(user);
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
    userImpl=(UserImpl) context.getBean("userImpl");
    List<User> users=userImpl.findAllUser();
    for(int i=0;i<users.size();i++){
        System.out.println(users.get(i));
    }
} 


} 
