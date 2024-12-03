package my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import my.utils.PropertyUtil;




public class DBHelper {
 
	public static Connection connDB() {
		Connection conn = null;
		try {
			String driver = PropertyUtil.getKeyValue("JDBC.DRIVER");
			String url = PropertyUtil.getKeyValue("JDBC.URL");
			String user = PropertyUtil.getKeyValue("MYSQLDB.USER");
			String password = PropertyUtil.getKeyValue("MYSQLDB.PASSWORD");
			Class.forName(driver);
			if (null == conn) {
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!damai");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
 

	// Release Sources [ResultSet]
	public static void free(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Release Source [Statement]
	public static void free(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Release Source [Connection]
	public static void free(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Release All Source [ResultSet, Statement, Connection]
	public static void free(ResultSet rs, Statement st, Connection conn) {
		free(rs);
		free(st);
		free(conn);
	}
	
	public static String getUUID(){
		String uuid = "";
		uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
		return uuid;
	}
}
