package cores;

import java.util.List;

/*
 * Query�ӿڣ������ѯ
 */

@SuppressWarnings("all")
public abstract class Query implements Cloneable{
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * ֱ��ִ��һ��DML���
	 * @param sql
	 * @param params
	 * @return ִ��sql����Ӱ����������
	 */
	public int excuteDML(String sql,Object[] params) {
		return 0;
	}
	
	/**
	 * ��һ��������뵽���ݿ���
	 * @param obj
	 */
	public void insert (Object obj) {
		
	}
	
	/**
	 * ɾ��clazz���Ӧ�ı��еļ�¼(ָ��id)
	 * @param clazz
	 * @param id
	 * @return
	 */
	public void delete(Class clazz,Object id) {
		
	}
	
	/**
	 * ɾ��clazz���Ӧ�ı��еļ�¼(�������ڵ����Ӧ���������ֵ��Ӧ����)
	 * @param obj
	 */
	public void delete(Object obj) {
		
	}
	
	/**
	 * ����ָ������ļ�¼������ֻ����ָ�������ֵ
	 * 
	 * @param obj
	 * @return
	 */
	public int update(Object obj,String[] fieldNames) {
		return 0;
	}
	
	/*
	 * ��ѯ��Ӧ�����������ѯһ��һ�У�һ�ж��У����ж���
	 */
	
	/**
	 * ��ѯ���м�¼������ÿ�м�¼��װ����Ķ�����
	 * @param sql
	 * @param clazz ��װ���ݵ�Javabean���Class����
	 * @param params sql����
	 * @return
	 */
	public List queryRows(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * ��ѯһ�м�¼
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * ��ѯһ��ֵ
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryValue(String sql,Class clazz,Object[] params) {
		return null;
	}
	
	/**
	 * ��ѯһ�����֣�Number���������ֵĳ���
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Number queryNumber(String sql,Class clazz,Object[] params) {
		return null;
	}
}
