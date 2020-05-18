package cores;

import java.util.List;

/*
 * Query接口：负责查询
 */

@SuppressWarnings("all")
public abstract class Query implements Cloneable{
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * 直接执行一个DML语句
	 * @param sql
	 * @param params
	 * @return 执行sql语句后影响的语句数量
	 */
	public int excuteDML(String sql,Object[] params) {
		return 0;
	}
	
	/**
	 * 将一个对象插入到数据库中
	 * @param obj
	 */
	public void insert (Object obj) {
		
	}
	
	/**
	 * 删除clazz类对应的表中的记录(指定id)
	 * @param clazz
	 * @param id
	 * @return
	 */
	public void delete(Class clazz,Object id) {
		
	}
	
	/**
	 * 删除clazz类对应的表中的记录(对象所在的类对应到表，对象的值对应主键)
	 * @param obj
	 */
	public void delete(Object obj) {
		
	}
	
	/**
	 * 更新指定对象的记录，并且只更新指定对象的值
	 * 
	 * @param obj
	 * @return
	 */
	public int update(Object obj,String[] fieldNames) {
		return 0;
	}
	
	/*
	 * 查询对应多种情况，查询一行一列，一行多列，多行多列
	 */
	
	/**
	 * 查询多行记录，并将每行记录封装到类的对象中
	 * @param sql
	 * @param clazz 封装数据的Javabean类的Class对象
	 * @param params sql参数
	 * @return
	 */
	public List queryRows(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * 查询一行记录
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * 查询一个值
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryValue(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * 查询一个数字，Number是所有数字的超类
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Number queryNumber(String sql,Class clazz,Object[] params) {
		return null;
	}
}
