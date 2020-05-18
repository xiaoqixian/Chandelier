package cores;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import bean.ColumnInfo;
import bean.TableInfo;
import utils.JavaFilesUtils;
import utils.StringUtils;

/*
 * ����������ݿ����б�ṹ����ṹ����Ϣ����������ṹ���ɱ�ṹ
 */
public class TableContext {
	
	/*
	 * ����Ϊkey,����Ϣ����Ϊvalue
	 */
	private static Map<String,TableInfo> tables = new HashMap<>();
	
	/**
	 * ��po��class���������Ϣ���������������������
	 */
	public static Map<Class,TableInfo> poClassTableMap = new HashMap<>();
	
	private TableContext() {};
	
	static {
		try {
			//��ʼ����ñ���Ϣ
			Connection con = DBManager.getCon();
			DatabaseMetaData dbmd = con.getMetaData(); //������ݿ��������Ϣ
			
			ResultSet rs = dbmd.getTables("sorm","%","%",new String[] {"TABLE"});
			
			while (rs.next()) {
				String tableName = (String)rs.getString("TABLE_NAME");
				TableInfo ti = new TableInfo(tableName,new ArrayList<ColumnInfo>(),new HashMap<String,ColumnInfo>());
				tables.put(tableName, ti);
				
				ResultSet set = dbmd.getColumns(null, "%", tableName,"%");
				
				while (set.next()) {
					ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0); //0��ʾ����ͨ��
					ti.getColumn().put(set.getString("COLUMN_NAME"),ci);
				}
				
				
				ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);//��ѯ������������
				

				while (set2.next()) {
					ColumnInfo ci2 = ti.getColumn().get(set2.getString("COLUMN_NAME"));
					if (ci2 != null) {
					ci2.setKeyType(1); //��Ϊ��������
					ti.getPriKeys().add(ci2);
					}
					//else System.out.println(set2.getObject("COLUMN_NAME"));
				}
				
				if (ti.getPriKeys().size() > 0) { //ȡΨһ�����������������������Ϊ��
					ti.setPrimeKey(ti.getPriKeys().get(0));
					
				}
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//ÿ������ʱ�������һ��
		updatePOFiles();
		
		
		//����Class�����µ�po��
		loadPOFiles();
	}
	
	public static Map<String,TableInfo> getTableInfos(){
		return tables;
	}
	
	public static void updatePOFiles() {
		for (TableInfo ti : tables.values()) {
			JavaFilesUtils.createJavaPOFile(ti, new MysqlTypeConverter());
		}
	}
	
	//����po���������
	public static void loadPOFiles() {
		for (TableInfo ti : tables.values()) {
			try {
				poClassTableMap.put(Class.forName(DBManager.getPoPackage() + "." + StringUtils.firstChar2UpperCase(ti.getName())), ti);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				File f = new File("");
				
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Map<String,TableInfo> tables = TableContext.getTableInfos();
		
	}
}
