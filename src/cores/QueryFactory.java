package cores;

/*
 * 与用户交接的一个类，使用时通过QueryFactory产生一个Query对象
 */
public class QueryFactory {
	
	private static QueryFactory factory = new QueryFactory();
	private static Query prototypeObj; //克隆的原型对象
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
