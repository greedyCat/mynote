package mine.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mine.note.vo.UserVo;

public class UserLogin extends BaseDao{

	public UserVo getUser(UserVo vo){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select userid, username,userpasswd,alias,status from userinfo where alias='").append(vo.getAlias());
		sql.append("' and userpasswd='").append(vo.getUserpasswd()).append("'");
		ResultSet rs = this.exeQuery(sql.toString());
		UserVo userVo = null;
		try {
			while(rs.next()){
				userVo = new UserVo();
			 	userVo.setAlias(rs.getString("alias"));
			 	userVo.setUsername(rs.getString("username"));
			 	userVo.setUserid(rs.getString("userid"));
			 	userVo.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		
		return userVo;
	}
}
