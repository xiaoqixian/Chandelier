package cores;

/*
 * ����Java�������������ݿ���������͵Ļ���ת��
 */
public interface TypeConvert {
	
	/**
	 * �����ݿ�����ת��ΪJava����
	 * @param columnType
	 * @return Java����
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * ��java����ת��Ϊ���ݿ�����
	 * @param javaType
	 * @return ���ݿ�����
	 */
	public String javaType2DatabaseType(String javaType);
}
