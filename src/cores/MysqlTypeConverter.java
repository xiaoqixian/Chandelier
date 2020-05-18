package cores;

/*
 * MySQL数据库的数据类型和java数据类型的转换
 */
public class MysqlTypeConverter implements TypeConvert{

	/**
	 * 本类只实现这个方法
	 */
	@Override
	public String databaseType2JavaType(String columnType) {
		if ("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
			return "String";
		}
		else if ("int".equalsIgnoreCase(columnType)
				|| "tinyInt".equalsIgnoreCase(columnType)
				|| "smallInt".equalsIgnoreCase(columnType)
				|| "Integer".equalsIgnoreCase(columnType)) {
			return "Integer";
		}
		else if ("bigint".equalsIgnoreCase(columnType)) {
			return "Long";
		}
		else if ("double".equalsIgnoreCase(columnType) || "float".equalsIgnoreCase(columnType)) {
			return "Double";
		}
		else if ("blob".equalsIgnoreCase(columnType)) {
			return "java.sql.Blob";
		}
		else if ("TIMESTAMP".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp";
		}
		else if ("DATE".equalsIgnoreCase(columnType)) {
			return "java.sql.Date";
		}
		else return columnType;
	}

	@Override
	public String javaType2DatabaseType(String javaType) {
		// TODO Auto-generated method stub
		return null;
	}

}
