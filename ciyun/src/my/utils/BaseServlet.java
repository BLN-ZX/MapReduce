package my.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("cmd");
		if(methodName==null || methodName.trim().equals("")){
			methodName="execute";
		}
		try{
			Method method = this.getClass()
								.getMethod(methodName,
										HttpServletRequest.class,
										HttpServletResponse.class);
			method.invoke(this,request,response);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public abstract void execute(HttpServletRequest request,HttpServletResponse response)
			throws Exception;
}
