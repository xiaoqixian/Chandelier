package utils;

/*
 * ��װString�ĳ��ò���
 */
public class StringUtils {
	
	/**
	 * ��һ���ַ���������ĸ��д
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
