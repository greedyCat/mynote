package mine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbHelper {

	private static final String userName = "root";
	private static final String userPasswd = "root";
	private static final String dbDriver = "com.mysql.jdbc.Driver";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/myrecord?useUnicode=true&characterEncoding=UTF-8";
	
    public static Connection getDbCon(){
    	
    	Connection conn = null;
    	try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, userName, userPasswd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
     
    	return conn;
    }
    
    public static void main(String[] args){
    	
    	DbHelper.getDbCon();
    }
}
