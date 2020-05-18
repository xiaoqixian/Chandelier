package cores;

/*
 * 负责Java数据类型与数据库的数据类型的互相转换
 */
public interface TypeConvert {
	
	/**
	 * 将数据库类型转换为Java类型
	 * @param columnType
	 * @return Java类型
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * 将java类型转换为数据库类型
	 * @param javaType
	 * @return 数据库类型
	 */
	public String javaType2DatabaseType(String javaType);
}
