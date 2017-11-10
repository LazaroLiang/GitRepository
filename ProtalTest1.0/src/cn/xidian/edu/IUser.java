package cn.xidian.edu;

import cn.xidian.edu.Dao.User;

import java.util.List;

public interface IUser {
    public int addUser(User user);
    public int updateUser(User user);
    public int deleteUser(int userId);
    public User findUserById(int userId);
    public List<User> findAllUser();
}
