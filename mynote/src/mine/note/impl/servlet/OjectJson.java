package mine.note.impl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.dao.RecordDao;
import mine.note.vo.RecordType;
import mine.note.vo.UserVo;
import mine.util.WebLevelUtil;

import com.google.gson.Gson;

public class OjectJson extends HttpServlet {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6895469749401186472L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 UserVo userVo =  WebLevelUtil.getUser(request);
	     List<RecordType> types = new ArrayList<RecordType>();
		 Gson gson = new Gson();
	     RecordDao dao = new RecordDao();
	     if(userVo!=null){
	    	types = dao.queryRecType(userVo.getUserid());
	        String jsonstr = gson.toJson(types);
	        PrintWriter writer = response.getWriter();
	    	writer.write(jsonstr);
	    	writer.close();
	    	
	     }else {
	    	 
	    	 request.setAttribute("msg", "session 过期,请重新登录！");
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		 
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    doGet(request, response);
	}

}
