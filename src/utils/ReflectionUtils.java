package utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * ��װ����ĳ��ò���
 */
public class ReflectionUtils {
	
	public static Object invokeGet(String methodName,Object obj) {
		try {
			Method m = obj.getClass().getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(methodName),null);
			return m.invoke(obj, null);//�ȿ����ڲ�֪�����������µ��ú�����get����
			//delete(c,priKeyValue);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void invokeSet(Object obj,String methodName,Object columnValue) {
		try {
			Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(methodName), columnValue.getClass());
			m.invoke(obj, columnValue);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File f = new File("src/po/Department.java");
		System.out.println(f.exists());
	}
}
