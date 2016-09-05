package mine.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Upload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6746484164642262730L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	   InputStream inputStream = request.getInputStream();
	   //String name = request.getParameter("namefile");
	   File file = new File("d:/temp","aaa.xlsx");
	   FileOutputStream fos = new FileOutputStream(file);
	   
       byte[] b = new byte[1024];
       int n=0;

       while((n=inputStream.read(b))!=-1){
          fos.write(b,0,n);
       }

       fos.close();
       inputStream.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	  doGet(request, response);
	}

}
