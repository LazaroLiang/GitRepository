package cn.xidian.edu.Service;

import cn.xidian.edu.Dao.User;
import cn.xidian.edu.DaoImpl.UserImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {
    private UserImpl userImpl;

    public void setUserImpl(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    public void testTransaction(User user){
        userImpl.addUser(user);
        int a=9/0;
        userImpl.addUser(user);
    }
}
