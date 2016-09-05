package mine.note.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.dao.RecordDao;
import mine.note.vo.RecordVo;
import mine.note.vo.UserVo;
import mine.util.WebLevelUtil;

public class DetailShowServlet extends HttpServlet {

 
	/**
	 * 详情展示
	 */
	private static final long serialVersionUID = -3256868510144947763L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       UserVo userVo =  WebLevelUtil.getUser(request);
       String tid = request.getParameter("id");
       if(userVo!=null){
    	   RecordDao dao = new RecordDao();
    	   RecordVo recordVo = dao.recordQueryById(tid);
    	   request.setAttribute("revo", recordVo);
    	   request.getRequestDispatcher("/recordcenter/infodetail.jsp").forward(request, response);  
       }
       else {
    	   request.setAttribute("msg", "session 过期,请重新登录！");
		   request.getRequestDispatcher("/login.jsp").forward(request, response);
	 }
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	   doGet(request, response);
	}

}
