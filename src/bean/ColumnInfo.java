package bean;

/**
 * 封装一个字段的信息
 * @author xqx
 *
 */
public class ColumnInfo {

	private String name; //字段名称
	private String dataType; //字段的数据类型
	private int keyType; //字段的键类型(0:普通键,1:主键,2:外键)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getKeyType() {
		return keyType;
	}
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}
	
	public ColumnInfo() {
		
	}
	
	public ColumnInfo(String name,String type,int keyType) {
		this.name = name;
		this.dataType = type;
		this.keyType = keyType;
	}
}
