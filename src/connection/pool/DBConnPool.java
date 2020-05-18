package connection.pool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cores.DBManager;

/**
 * ���ӳأ����ڴ��Connection�����List�������ظ�ʹ�ã���ΪConnction����Ĵ����ܷ�ʱ��
 * ���ӳص���
 * @author xqx
 *
 */
public class DBConnPool {
	private static List<Connection> pool; //���ӳض���
	
	/**
	 * ���ӳص������������С�������ȸĳɷ����������ļ��У����û�����
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
	
	public void initPool() {
		if (pool == null) {
			pool = new ArrayList<>();
		}
		
		while (pool.size() < POOL_MIN_SIZE) {
			pool.add(DBManager.createCon());
			System.out.println("��ʼ��������Ϊ��" + pool.size());
		}
	}
	
	public DBConnPool() {
		initPool();
	}
	
	/**
	 * �����ӳ���ȡ��һ������
	 * @return
	 */
	public synchronized Connection getConnection() { //synchronized��֤һ��ֻ��һ���̷߳����������
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
