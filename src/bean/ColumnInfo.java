package bean;

/**
 * ��װһ���ֶε���Ϣ
 * @author xqx
 *
 */
public class ColumnInfo {

	private String name; //�ֶ�����
	private String dataType; //�ֶε���������
	private int keyType; //�ֶεļ�����(0:��ͨ��,1:����,2:���)
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
