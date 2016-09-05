package mine.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.note.vo.UserVo;


public class WebLevelUtil {
	public final static String USER = "sessionUser";
	
	/**
	 * 向session中存入用户
	 * @param user
	 * @param request
	 */
	public static void setUser(UserVo user, HttpServletRequest request,HttpServletResponse response){
		clearUser(request,response);
		request.getSession().setAttribute(USER, user);
	}
	
	/**
	 * 获取session中用户对象
	 * @param request
	 * @return
	 */
	public static UserVo getUser(HttpServletRequest request){
		return (UserVo) request.getSession().getAttribute(USER);
	}
	
	/**
	 * 清除session中用户对象
	 * @param request
	 */
	public static void clearUser(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", 0);
		request.getSession().removeAttribute(USER);
	}
	/**
	 * 清除session中菜单列表
	 * @param request
	 */
	public static void clearMenuList(HttpServletRequest request) {
		request.getSession().removeAttribute("menuList");
	}
	/**
	 * 获取session中用户对象用户编码
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request){
		UserVo vo = getUser(request);
		return vo == null ? "0" : vo.getUserid();
	}
}
