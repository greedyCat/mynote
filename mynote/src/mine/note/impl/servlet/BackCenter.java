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

public class BackCenter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1436273179172539515L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          UserVo user = WebLevelUtil.getUser(request);
          List<RecordVo> rvos = new ArrayList<RecordVo>();
		  List<RecordType> recvo = new ArrayList<RecordType>();
          if(user!=null){
        	  recvo = queryUserNoteType(user);  
        	  rvos = queryUserNote(user);
        	  request.setAttribute("records", rvos);
        	  request.setAttribute("recordtype",recvo);	 
        	  request.getRequestDispatcher("/recordcenter/infocenter.jsp").forward(request, response);
          }else {
        	  request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	private List<RecordType> queryUserNoteType(UserVo vo) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		return dao.queryRecType(vo.getUserid());
	}

	private List<RecordVo> queryUserNote(UserVo user) {
		// TODO Auto-generated method stub
		RecordDao dao = new RecordDao();
		return dao.recordQuery(user.getUserid());
	}
}
