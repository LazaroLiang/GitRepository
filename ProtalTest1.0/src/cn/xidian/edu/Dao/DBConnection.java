package cn.xidian.edu.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DBConnection extends JdbcDaoSupport{
//    public void getJdbcTemplate(){
//
//    }
    public JdbcTemplate jdbcTemplate=getJdbcTemplate();

    public JdbcTemplate getJdbcConnect() {
        if(jdbcTemplate==null){
            System.out.println("jdbcTemplate null");
        }
        return jdbcTemplate;
    }
}
