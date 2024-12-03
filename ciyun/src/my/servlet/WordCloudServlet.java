package my.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import my.domain.WordCloud;
import my.manager.WordCloudManager;
import my.utils.BaseServlet;
import net.sf.json.JSONArray;


public class WordCloudServlet extends BaseServlet{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static Logger logger = Logger.getLogger(WordCloudServlet.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		
		//String opt = request.getParameter("opt");
		WordCloudManager scale= new WordCloudManager();
		
		//if (opt.equals("WordCloud")) {
			List<WordCloud> list = new ArrayList<WordCloud>();
			list = scale.getWordCloudList();
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.err.println(jsonArray);
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		//}
	}
}
