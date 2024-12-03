package my.utils;
import java.sql.Connection;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class DataSourceUtils {
	private static DataSource ds;

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static {
		ds = 
		new ComboPooledDataSource();
	}
	public static DataSource getDatasSource() {
		return ds;
	}

	public static Connection getConn(){
		Connection con = null;
		
		con = tl.get();
		if(con==null){
			try{
				con = getDatasSource().getConnection();
			
				tl.set(con);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return con;
	}
	public static void remove() {
		tl.remove();
	}
}
