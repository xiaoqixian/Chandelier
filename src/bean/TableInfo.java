package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �洢��ṹ����Ϣ
 * @author xqx
 *
 */
public class TableInfo {
	private String name;
	private Map<String,ColumnInfo> column; //�����ֶε���Ϣ
	private ColumnInfo primeKey; //���Ǳ���ֻ��һ�����������
	private List<ColumnInfo> priKeys;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, ColumnInfo> getColumn() {
		return column;
	}
	
	public void setColumn(Map<String, ColumnInfo> column) {
		this.column = column;
	}
	
	public ColumnInfo getPrimeKey() {
		return primeKey;
	}
	
	public void setPrimeKey(ColumnInfo primeKey) {
		this.primeKey = primeKey;
	}
	
	public TableInfo(String name, Map<String, ColumnInfo> column, ColumnInfo primeKey) {
		super();
		this.name = name;
		this.column = column;
		this.primeKey = primeKey;
	}

	public TableInfo() {
		
	}

	public TableInfo(String tableName, List<ColumnInfo> priKeys, HashMap<String, ColumnInfo> hashMap) {
		// TODO Auto-generated constructor stub
		super();
		this.name = tableName;
		this.priKeys = priKeys;
		this.column = hashMap;
	}

	public List<ColumnInfo> getPriKeys() {
		return priKeys;
	}

	public void setPriKeys(List<ColumnInfo> priKeys) {
		this.priKeys = priKeys;
	}
	
	
	
}
