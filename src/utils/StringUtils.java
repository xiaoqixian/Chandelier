package utils;

/*
 * 封装String的常用操作
 */
public class StringUtils {
	
	/**
	 * 将一个字符串的首字母大写
	 * @param string
	 * @return
	 */
	public static String firstChar2UpperCase(String string ) {
		StringBuilder sBuilder = new StringBuilder(string);
		sBuilder.setCharAt(0, Character.toUpperCase(string.charAt(0)));
		return sBuilder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(firstChar2UpperCase("inaflaf"));
	}
}
