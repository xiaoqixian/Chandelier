package cores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSException;

import bean.ColumnInfo;
import bean.TableInfo;
import po.Department;
import po.Employee;
import utils.JDBCUtils;
import utils.ReflectionUtils;
import utils.StringUtils;
import java.lang.reflect.Field;

/**
 * 针对mysql的查询
 * @author xqx
 *
 */
public class MysqlQuery implements Query{
	
	public void test() {
		Employee emp = new Employee();
		emp.setId(4);
		emp.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		emp.setName("Sue");
		emp.setSalary((double) 20000);
		
		new MysqlQuery().update(emp,new String[] {"name","salary","birthday"});
	}
	
	public static void main(String[] args) {
		List<Object> l = new MysqlQuery().queryRows("select id,name,salary from employee where salary > ?",Employee.class,new Object[] {10000});
		
		for (Object o : l) {
			Employee e = (Employee)o;
			System.out.println(e.getName());
		}
	}

	@Override
	public int excuteDML(String sql, Object[] params) {
		Connection con = DBManager.getCon();
		int count = 0;//执行语句的条数
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			
			//给sql设参
			JDBCUtils.handleParams(ps, params);
			
			count = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.closeConnection(ps,con);
		}
		return count;
	}

	/**
	 * 将一个对象插入到数据库中
	 * 只存入不为null的属性
	 */
	@Override
	public void insert(Object obj) {
		Class c = obj.getClass();
		TableInfo ti = TableContext.poClassTableMap.get(c);
		List<Object> params = new ArrayList<>();
		
		//通过反射调用get方法或set方法
		
		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(ti.getName() + " (");
		
		Field[] fs = c.getDeclaredFields();
		for (Field f : fs) {
			String fieldName = f.getName();
			Object fieldValue = ReflectionUtils.invokeGet(fieldName, obj);
			
			if (fieldValue != null) { //只存入不为null的值
				sql.append(fieldName + ",");
				params.add(fieldValue);
			}
		}
		
		sql.deleteCharAt(sql.length() - 1);//把最末尾多余的逗号去掉
		sql.append(") values (");
		for (int i = 0;i < params.size();i++) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length() - 1, ')');
		System.out.println(sql);
		excuteDML(sql.toString(),params.toArray());
	}

	@Override
	public void delete(Class clazz, Object id) {
		// 通过Class对象找表结构
		TableInfo ti = TableContext.poClassTableMap.get(clazz);
		//获得主键
		ColumnInfo priKey = ti.getPrimeKey();
		
		String sql = "delete from " + ti.getName() + " where " + priKey.getName() + "= ?";
		excuteDML(sql,new Object[] {id});
	}

	@Override
	public void delete(Object obj) {
		TableInfo ti = TableContext.poClassTableMap.get(obj.getClass());
		ColumnInfo priKey = ti.getPrimeKey();
		//System.out.println(priKey == null);
		//System.out.println(priKey.getName());
		
		//通过反射调用get方法或set方法
		Object priKeyValue = ReflectionUtils.invokeGet("get" + StringUtils.firstChar2UpperCase(priKey.getName()), obj);
		delete(obj.getClass(),priKeyValue);
	}

	@Override
	public int update(Object obj, String[] fieldNames) {
		// 传入参数object{uname,pwd} --> update 表名 uname = ?, pwd = ? where id = ?
		StringBuilder sql = new StringBuilder("update ");
		List<Object> params = new ArrayList<>();
		Class c = obj.getClass();
		TableInfo ti = TableContext.poClassTableMap.get(c);
		ColumnInfo primeKey = ti.getPrimeKey();
		
		sql.append(ti.getName() + " set ");
		Field[] fs = c.getDeclaredFields();
		for (String s : fieldNames) {
			sql.append(s + " = ?,");
			params.add(ReflectionUtils.invokeGet(s, obj));
		}
		sql.setCharAt(sql.length() - 1, ' ');
		sql.append("where id = ?");
		params.add(ReflectionUtils.invokeGet(primeKey.getName(), obj));
		
		excuteDML(sql.toString(),params.toArray());
		return 0;
	}

	@Override
	public List<Object> queryRows(String sql, Class clazz, Object[] params) {
		Connection con = DBManager.getCon();
		List<Object> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(sql);
			JDBCUtils.handleParams(ps, params);
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				if (list == null) list = new ArrayList<>();
				@SuppressWarnings("deprecation")
				Object rowObj = clazz.newInstance(); //调用JavaBean的无参构造器
				
				for (int i = 0;i < metaData.getColumnCount();i++) { //sql语句中有多少查询的参数，则遍历多少次
					String columnName = metaData.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(i + 1);
					
					//反射调用set方法
					ReflectionUtils.invokeSet(rowObj, columnName, columnValue);
				}
				
				list.add(rowObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		List<Object> l = queryRows(sql, clazz, params);
		return (l == null || l.size() == 0) ? null : l.get(0);
	}

	@Override
	public Object queryValue(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number queryNumber(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

}
