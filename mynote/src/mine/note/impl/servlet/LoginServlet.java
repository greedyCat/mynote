package mine.note.impl.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.dao.UserLogin;
import mine.note.vo.UserVo;
import mine.util.WebLevelUtil;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486636463972139446L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getContextPath();
		String username = request.getParameter("username");
		String userpassed = request.getParameter("userpasswd");
		UserLogin login = new UserLogin();
		UserVo userVo = new UserVo();
		userVo.setAlias(username);
		userVo.setUserpasswd(userpassed);
		UserVo vo = login.getUser(userVo);
		if(vo!=null){
			if("1".equals(vo.getStatus())){
			 WebLevelUtil.setUser(vo, request, response);
			/* List<RecordVo> rvos = queryUserNote(vo);
			 List<RecordType> recvo = queryUserNoteType(vo);
			 request.setAttribute("records", rvos);
			 request.setAttribute("recordtype",recvo);*/
			 response.sendRedirect(url+"/servlet/BackCenter");
			 //request.getRequestDispatcher("/servlet/BackCenter").forward(request, response);
			}
			else {
				request.setAttribute("msg", "该用户已没有权限!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doGet(request, response);
	}

}
