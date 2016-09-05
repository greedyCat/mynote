package mine.note.impl.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.dao.RecordDao;
import mine.note.vo.RecordVo;

public class MyInfo extends HttpServlet {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4265264912747290325L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          
		  request.setCharacterEncoding("utf-8");
		  String method = request.getParameter("method");
		  if("create".equals(method)){
			  RecordVo vo = new RecordVo();
			  vo.setRecordtitle(request.getParameter("title"));
			  vo.setRecordinfo(request.getParameter("content"));
			  vo.setTypeid(request.getParameter("typenote"));
			  vo.setTypename(request.getParameter("typename"));
			  create(vo,request);
		  }
		  if("delete".equals(method)){
			  String noteid = request.getParameter("noteid");
			  delete(noteid);
		  }
		  if("edit".equals(method)){
			  edit();
		  }
		 
	}

	private void edit() {
		 
		
	}

	private void delete(String noteid) {
		  
		RecordDao dao = new RecordDao();
		dao.recordDel(noteid);
	}

	private void create(RecordVo vo,HttpServletRequest request) {
		
		RecordDao dao = new RecordDao();
		dao.recordAdd(vo,request);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
