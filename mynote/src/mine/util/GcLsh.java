package mine.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GcLsh {
	
	
	
	public static String getLsh(String typeid,String deskno){
		if(StringUtils.isBlank(typeid)||StringUtils.isBlank(deskno)){
			return null;
		}
		Connection conn = null;
		Statement stmt = null;
		
		//取出当前的流水号
		String currentLsh = "";
		String endLsh = "";
		StringBuffer sql= new StringBuffer();
		sql.append("select * from gc_lsh where typeid='").append(typeid).append("' and deskno='").append(deskno).append("'");
		try {
			conn = DbHelper.getDbCon();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while(rs!=null&&rs.next()){
				currentLsh = rs.getString("clsh");
				endLsh = rs.getString("elsh");
			}
			if(StringUtils.isNotBlank(currentLsh)){//取到则+1后更新到数据库
				long newLshInt = Long.valueOf("1"+currentLsh)+1;
				String newLshStr = String.valueOf(newLshInt).substring(1);
				//若新的流水号大于结束流水号则返回空
				if(StringUtils.isNotBlank(endLsh)&&Long.valueOf(newLshStr)>Long.valueOf(endLsh)){
					return null;
				}else{
					updateLsh(typeid,deskno,newLshStr,conn);
					return newLshStr;
				}
			}else{//没有取到则创建一条新数据
				String clsh = "000001";
				String slsh = "000001";
				String elsh = "999999";
				insertLsh(typeid,deskno,clsh,slsh,elsh,conn);
				return clsh;
			}
			
		} catch (SQLException e) {
			 
		   e.printStackTrace();
		}  finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
				 
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					 
				}
			}
		}
		return null;
	}
	/**
	 * 更新流水号
	 * @param typeid
	 * @param deskno
	 * @param clsh
	 * @param conn
	 * @throws SQLException
	 */
	private static void updateLsh(String typeid,String deskno,String clsh,Connection conn) throws SQLException{
		
		Statement stmt = conn.createStatement();
		StringBuffer sql= new StringBuffer();
		sql.append("update gc_lsh set clsh ='").append(clsh).append("' where typeid='").append(typeid).append("' and deskno='").append(deskno).append("'");
		stmt.executeUpdate(sql.toString());
		stmt.close();
	}
	private static void insertLsh(String typeid,String deskno,String clsh,String slsh,String elsh,Connection conn) throws SQLException{
		Statement stmt = conn.createStatement();
		StringBuffer sql= new StringBuffer();
		sql.append("insert into gc_lsh values('").append(typeid).append("','").append(clsh).append("','").append(slsh).append("','").append(elsh).append("','").append(deskno).append("','')");
		stmt.executeUpdate(sql.toString());
		stmt.close();
	}
	

}
