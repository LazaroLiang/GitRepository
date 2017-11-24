package cn.xidian.edu.Dao.DaoImpl;

import cn.xidian.edu.Bean.User;
import cn.xidian.edu.Dao.IUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class UserImpl extends JdbcDaoSupport implements IUser{

    @Override
    public int addUser(User user) {
        String sql="insert into user(user_name,user_type,user_password) values(?,?,?)";
        int res=getJdbcTemplate().update(sql,new Object[]{user.getUser_name(),user.getUser_type(),user.getUser_password()});
        return res;
    }

    @Override
    public int updateUser(User user) {
        String sql="update user set user_name=?,usr_type=?,user_password=? where user_id=?";
        int res=getJdbcTemplate().update(sql,new Object[]{user.getUser_name(),user.getUser_type(),user.getUser_password(),user.getUser_id()});
        return res;
    }

    @Override
    public int deleteUser(int userId) {
        String sql="delete user where user_id=?";
        int res=getJdbcTemplate().update(sql,userId);
        return userId;
    }

    @Override
    public User findUserById(int userId) {
        String sql="select * from user where user_id=?";
        User user=(User) getJdbcTemplate().queryForObject(sql,new Object[]{userId},new BeanPropertyRowMapper(User.class));
        return user;
    }

    @Override
    public List<User> findAllUser() {
        String sql="select * from user";
        List<User> userList=getJdbcTemplate().query(sql,new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

}
