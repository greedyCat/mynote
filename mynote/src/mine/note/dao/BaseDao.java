package mine.note.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import mine.util.DbHelper;

public class BaseDao {

	Connection conn = null;
	PreparedStatement pst = null;
	// query database
	protected ResultSet exeQuery(String sql){
		
		ResultSet rs = null;
		if(conn==null){
			conn = DbHelper.getDbCon();
		}
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			 
			rs = pst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return rs;
	}
	//database query by page
	protected ResultSet executeQuery(String sql, String pageNo, String maxCount) {
		ResultSet rs = null;
		try { 
			int dataNo = (Integer.valueOf(pageNo) - 1)
					* Integer.valueOf(maxCount);
			sql = sql + " limit " + String.valueOf(dataNo) + "," + maxCount;
			if (conn == null) {
				conn = DbHelper.getDbCon();
			}
			pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
		} catch (Exception e) {
            e.printStackTrace();
		
		} 
		return rs;
	}

	//chage datebase
	
	protected boolean exeUpdate(String sql){
		
		boolean flag = false;
		int num = 0;
		if(conn==null){
			conn = DbHelper.getDbCon();
		}
		try {
	       pst = (PreparedStatement) conn.prepareStatement(sql);
		   num = pst.executeUpdate(sql);
		   if(num>0){
			   flag = true;
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	protected void closeResult(ResultSet rs){
		
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try {
				pst.close();
				pst=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
		   try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
	protected void closeUpdate(){
	
		if(pst!=null){
			try {
				pst.close();
				pst=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
		   try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
//	public static void main (String[] args){
//		
//		BaseDao baseDao = new BaseDao();
//		boolean flag = baseDao.exeUpdate("insert into userinfo(username,userpasswd)values('yuanye','123456a')");
//	 
//       if(flag){
//		 System.out.println("ok!");
//		 }
//		 
//	    baseDao.closeUpdate();
// 
//	}
}
