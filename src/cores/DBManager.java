package cores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


import bean.Configuration;
import connection.pool.DBConnPool;

/*
 * 根据配置信息，负责数据库连接对象的管理(增加连接池信息)
 */
public class DBManager {
	private static Configuration conf;
	
	/**
	 * 连接池对象
	 */
	private static DBConnPool pool;
	
	static {
		Properties pro = new Properties();
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("databaseInfo.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conf = new Configuration();
		conf.setDriver(pro.getProperty("driver"));
		conf.setName(pro.getProperty("name"));
		conf.setPoPackage(pro.getProperty("poPackage"));
		conf.setPwd(pro.getProperty("pwd"));
		conf.setSrcPath(pro.getProperty("srcPath"));
		conf.setUrl(pro.getProperty("url"));
		conf.setUsingDB(pro.getProperty("usingDB"));
		conf.setQueryClass(pro.getProperty("queryClass"));
		conf.setPoolMaxSize(Integer.valueOf(pro.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.valueOf(pro.getProperty("poolMinSize")));
	}
	
	/*
	 * 数据库的连接和断开
	 */
	
	public static Connection getCon() {
		if (pool == null) {
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}
	
	public static Connection createCon() {
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),conf.getName(),conf.getPwd()); //目前直接建立连接，后期增加连接池处理，提高效率
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection con) {
		pool.putConnection(con);
	}
	
	public static void closeConnection(Statement statement,Connection con) {
		try {
			statement.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPoPackage() {
		return conf.getPoPackage();
	}
	
	public static String getSrcPath() {
		return conf.getSrcPath();
	}
	
	public static Configuration getConf() {
		return conf;
	}
	
}
