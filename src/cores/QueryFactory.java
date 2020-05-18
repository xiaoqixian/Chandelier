package cores;

/*
 * ���û����ӵ�һ���࣬ʹ��ʱͨ��QueryFactory����һ��Query����
 */
public class QueryFactory {
	
	private static QueryFactory factory = new QueryFactory();
	private static Query prototypeObj; //��¡��ԭ�Ͷ���
	private static Class c;
	
	static {
		try {
			c = Class.forName(DBManager.getConf().getQueryClass());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Query createQuery() throws CloneNotSupportedException {
		return (Query) prototypeObj.clone();
	}
	
	private QueryFactory() {
		
	}

}
