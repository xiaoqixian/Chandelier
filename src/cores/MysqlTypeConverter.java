package cores;

/*
 * MySQL���ݿ���������ͺ�java�������͵�ת��
 */
public class MysqlTypeConverter implements TypeConvert{

	/**
	 * ����ֻʵ���������
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
