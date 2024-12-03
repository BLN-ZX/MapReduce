package my.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.domain.WordCloud;

import org.apache.log4j.Logger;

public class WordCloudDao {
public static Logger logger = Logger.getLogger(WordCloudDao.class);
	
	public static List<WordCloud> getWordCloudList() {
		
		String sql = "select kw, max(num) as num from kwcloud group by kw order by num desc limit 30;";
		Connection conn=  null;
		ResultSet set = null;
		Statement stmt = null;
		List<WordCloud> list = new ArrayList<WordCloud>();
		
		try {
			conn = DBHelper.connDB();
			stmt = conn.createStatement();
			set = stmt.executeQuery(sql);
			while (set.next()) {
				WordCloud bean = new WordCloud();
				bean.setIsmobile(set.getString("kw"));
				bean.setNum(set.getInt("num"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			DBHelper.free(set, stmt, conn);
		}
		return list;
	}

}
