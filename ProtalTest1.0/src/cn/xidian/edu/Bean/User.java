/*
* author:@JerryLiang
* date:2017.11.08
* function:user object class
* */
package cn.xidian.edu.Bean;

public class User {
//    user_id int not null primary key,
//    user_name varchar(50) not null,
//    user_type varchar(10),
//    user_password char(6) not null
    private int user_id;
    private String user_name;
    private String user_type;
    private String user_password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString(){
        return "[ "+this.user_name+" "+this.user_password+" ]";
    }
}
