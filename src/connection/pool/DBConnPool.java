package connection.pool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cores.DBManager;

/**
 * 连接池：用于存放Connection对象的List，便于重复使用，因为Connction对象的创立很费时间
 * 连接池的类
 * @author xqx
 *
 */
public class DBConnPool {
	private static List<Connection> pool; //连接池对象
	
	/**
	 * 连接池的最大容量和最小容量，先改成放置在配置文件中，由用户配置
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
	
	public void initPool() {
		if (pool == null) {
			pool = new ArrayList<>();
		}
		
		while (pool.size() < POOL_MIN_SIZE) {
			pool.add(DBManager.createCon());
			System.out.println("初始化连接数为：" + pool.size());
		}
	}
	
	public DBConnPool() {
		initPool();
	}
	
	/**
	 * 从连接池中取出一个连接
	 * @return
	 */
	public synchronized Connection getConnection() { //synchronized保证一次只有一条线程访问这个方法
		int lastIndex = pool.size() - 1;
		Connection con = pool.get(lastIndex);
		pool.remove(lastIndex);
		return con;
	}
	
	public synchronized void putConnection(Connection con) {
		if (pool.size() >= POOL_MAX_SIZE) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			pool.add(con);
		}
	}
	
}
