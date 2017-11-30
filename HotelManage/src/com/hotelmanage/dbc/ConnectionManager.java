package com.hotelmanage.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//�������ݿ����
public class ConnectionManager {
	Connection conn = null;

	public Connection GetConn() { // �������ݿ⺯��

		String driverClassName = Env.getInstance().getProperty("driver");
		String url = Env.getInstance().getProperty("url");
		String password = Env.getInstance().getProperty("password");
		String user = Env.getInstance().getProperty("user");
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
