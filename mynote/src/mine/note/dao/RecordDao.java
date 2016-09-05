package mine.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mine.note.vo.RecordType;
import mine.note.vo.RecordVo;
import mine.note.vo.UserVo;
import mine.util.GcLsh;
import mine.util.HtmlRegexpUtil;
import mine.util.TimeUtil;
import mine.util.WebLevelUtil;

public class RecordDao extends BaseDao{

	public List<RecordVo> recordQueryByPage(String pageNo,String maxCount){
	
		List<RecordVo> recordVos = new ArrayList<RecordVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("select recordid,recordtitle,recordinfo,ctime,cusername,location,typeid,typename from recordinfo where status=0");
		ResultSet rs = this.executeQuery(sql.toString(),pageNo,maxCount);
		RecordVo recordVo = null;
		try {
			while (rs.next()) {
			    recordVo = new RecordVo();
				recordVo.setRecordid(rs.getString("recordid"));
			    recordVo.setRecordtitle(rs.getString("recordtitle"));
			    recordVo.setRecordinfo(rs.getString("recordinfo"));
			    recordVo.setCusername(rs.getString("cusername"));
			    recordVo.setCtime(rs.getString("ctime"));
			    recordVo.setLocation(rs.getString("location"));
			    recordVo.setTypeid(rs.getString("typeid"));
			    recordVo.setTypename(rs.getString("typename"));
		        recordVos.add(recordVo);
			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return recordVos;
	}
	
	public List<RecordVo> recordQuery(String userid){
		
		List<RecordVo> recordVos = new ArrayList<RecordVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("select recordid,recordtitle,recordinfo,ctime,cusername,typeid,typename from recordinfo where status=1 and cuserid='");
		sql.append(userid).append("' order by ctime desc");
		ResultSet rs = this.exeQuery(sql.toString());
		RecordVo recordVo = null;
		try {
			while (rs.next()) {
			    recordVo = new RecordVo();
				recordVo.setRecordid(rs.getString("recordid"));
			    recordVo.setRecordtitle(rs.getString("recordtitle"));
			    recordVo.setRecordinfo(HtmlRegexpUtil.filterHtml(rs.getString("recordinfo")));
			    recordVo.setCusername(rs.getString("cusername"));
			    recordVo.setCtime(rs.getString("ctime"));
			    recordVo.setTypeid(rs.getString("typeid"));
			    recordVo.setTypename(rs.getString("typename"));
			    recordVo.setPastTime(TimeUtil.pastTime(rs.getString("ctime")));
		        recordVos.add(recordVo);
			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return recordVos;
	}
	
  public RecordVo recordQueryById(String id){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select recordtitle,recordinfo,ctime,cusername,typename from recordinfo where status=1 and recordid='");
		sql.append(id).append("'");
		ResultSet rs = this.exeQuery(sql.toString());
		RecordVo recordVo = null;
		try {
			while (rs.next()) {
			    recordVo = new RecordVo();
			    recordVo.setRecordtitle(rs.getString("recordtitle"));
			    recordVo.setRecordinfo(rs.getString("recordinfo"));
			    recordVo.setCusername(rs.getString("cusername"));
			    recordVo.setCtime(rs.getString("ctime"));
			    recordVo.setTypename(rs.getString("typename"));
			    recordVo.setPastTime(TimeUtil.pastTime(rs.getString("ctime")));
		     			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return recordVo;
	}
  
  public  List<RecordVo> recordQueryByType(String typeid,String userid){
		
	    List<RecordVo> recordVos = new ArrayList<RecordVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("select recordid,recordtitle,recordinfo,ctime,cusername,typeid,typename from recordinfo where status=1 and typeid='");
		sql.append(typeid).append("' and cuserid='").append(userid).append("' order by ctime desc");
		ResultSet rs = this.exeQuery(sql.toString());
		RecordVo recordVo = null;
		try {
			while (rs.next()) {
			    recordVo = new RecordVo();
				recordVo.setRecordid(rs.getString("recordid"));
			    recordVo.setRecordtitle(rs.getString("recordtitle"));
			    recordVo.setRecordinfo(HtmlRegexpUtil.filterHtml(rs.getString("recordinfo")));
			    recordVo.setCusername(rs.getString("cusername"));
			    recordVo.setCtime(rs.getString("ctime"));
			    recordVo.setTypeid(rs.getString("typeid"));
			    recordVo.setTypename(rs.getString("typename"));
			    recordVo.setPastTime(TimeUtil.pastTime(rs.getString("ctime")));
		        recordVos.add(recordVo);
			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return recordVos;
	}
	public boolean recordAdd(RecordVo vo,HttpServletRequest request){
		
		boolean isSuccess = false;
		String noteid = GcLsh.getLsh("recordinfo", "1");
		UserVo user = WebLevelUtil.getUser(request);
		StringBuffer sql = new StringBuffer();
		sql.append("insert into recordinfo (recordid,recordtitle,recordinfo,ctime,cuserid,cusername,typeid,status,typename) values('");
		sql.append(noteid).append("','").append(vo.getRecordtitle()).append("','").append(vo.getRecordinfo()).append("','");
		sql.append(TimeUtil.getCurrentTime()).append("','").append(user.getUserid()).append("','").append(user.getAlias()).append("','");
		sql.append(vo.getTypeid()).append("','").append("1").append("','").append(vo.getTypename()).append("')");
		
		isSuccess = this.exeUpdate(sql.toString());
		this.closeUpdate();
		return isSuccess;
	}
	
     public boolean recordUpdate(RecordVo vo){
		
		boolean isSuccess = false;
		StringBuffer sql = new StringBuffer();
		sql.append("update recordinfo set recordtitle='").append(vo.getRecordtitle()).append("',recordinfo='").append(vo.getRecordinfo());
		sql.append("',location='").append(vo.getLocation()).append("' where recordid=").append(vo.getRecordid());
		isSuccess = this.exeUpdate(sql.toString());
		this.closeUpdate();
		return isSuccess;
	}
     
     public boolean recordDel(String recordid){
 		
 		boolean isSuccess = false;
 		StringBuffer sql = new StringBuffer();
 		sql.append("update recordinfo set status='").append("0").append("' where recordid='").append(recordid).append("'");
 		isSuccess = this.exeUpdate(sql.toString());
 		this.closeUpdate();
 		return isSuccess;
 	}
     
     public List<RecordType> queryRecType(String userid){
    	 
    	 List<RecordType> vos = new ArrayList<RecordType>();
    	 RecordType vo = null;
    	 StringBuffer sql = new StringBuffer();  
    	 sql.append("select typeid,typename from recordtype where status='1' and cuserid='").append(userid).append("' order by ctime asc");
    	 ResultSet rs = this.exeQuery(sql.toString());
    	 try {
			while(rs.next()){
			  vo = new RecordType();
			  vo.setTypeid(rs.getString("typeid"));
			  vo.setTypename(rs.getString("typename"));
			  vos.add(vo);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
    	return vos;
    	 
     }
     
     public  boolean newType(String name,String userid){
    	 
    	 boolean flag = false;
    	 String typeid = GcLsh.getLsh("recordtype", "1");
    	 StringBuffer sql = new StringBuffer();
    	 sql.append("insert into recordtype(typeid,typename,ctime,cuserid,status)values('").append(typeid).append("','").append(name).append("','");
    	 sql.append(TimeUtil.getCurrentTime()).append("','").append(userid).append("','").append("1").append("')");
         flag = this.exeUpdate(sql.toString());
         return flag;
     }
     
     public  boolean newTypeUpdate(String name,String typeid){
    	 
    	 boolean flag = false;
    	 
    	 StringBuffer sql = new StringBuffer();
    	 sql.append("update recordtype set typename='").append(name).append("' where typeid='").append(typeid).append("'");
    	 flag = this.exeUpdate(sql.toString());
         return flag;
     }
}
