package mine.note.impl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.dao.RecordDao;
import mine.note.vo.RecordType;
import mine.note.vo.RecordVo;
import mine.note.vo.UserVo;
import mine.util.WebLevelUtil;

public class NewType extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4499317182349509047L;

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 UserVo userVo =  WebLevelUtil.getUser(request);	 
		 String method = request.getParameter("method");
		 if(userVo!=null){
		  if("add".equals(method)){
			 String typename = request.getParameter("typename"); 
			 addType(typename,userVo.getUserid());
		  }
		  if("delete".equals(method)){
			  
		  }
		  if("update".equals(method)){
			  String typeid = request.getParameter("typeid");
			  String typename = request.getParameter("typename"); 			  
			  updateType(typename,typeid);
		  }
		  if("query".equals(method)){
			  String typeid = request.getParameter("typeid"); 
			  List<RecordType> recvo = new ArrayList<RecordType>();
			  List<RecordVo> records = queryType(typeid,userVo.getUserid());
			  recvo = queryUserNoteType(userVo); 
			  request.setAttribute("recordtype",recvo);	 
			  request.setAttribute("records", records);
			  request.getRequestDispatcher("/recordcenter/infocenter.jsp").forward(request, response);
		  }
		 }else {
			 request.setAttribute("msg", "session 过期,请重新登录！");
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	 
	}

	 
	private List<RecordVo> queryType(String typeid, String id) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		List<RecordVo> records = dao.recordQueryByType(typeid, id);
		return records;
	}

	private List<RecordType> queryUserNoteType(UserVo vo) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		return dao.queryRecType(vo.getUserid());
	}
	private void updateType(String typename, String typeid) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		dao.newTypeUpdate(typename,typeid);
	}


	private void addType(String typename,String id) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		dao.newType(typename, id);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          doGet(request, response);
	}

}
