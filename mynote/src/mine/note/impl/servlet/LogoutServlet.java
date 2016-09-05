package mine.note.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.vo.UserVo;
import mine.util.WebLevelUtil;

public class LogoutServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7123401405848445407L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   String url = request.getContextPath();
		   UserVo userVo = WebLevelUtil.getUser(request);
		   if(userVo!=null){
			   WebLevelUtil.clearUser(request, response);
		   }
		   response.sendRedirect(url+"/recordcenter/infocenter.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
