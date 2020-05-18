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
 * 负责管理数据库所有表结构和类结构的信息，并根据类结构生成表结构
 */
public class TableContext {
	
	/*
	 * 表名为key,表信息对象为value
	 */
	private static Map<String,TableInfo> tables = new HashMap<>();
	
	/**
	 * 将po的class对象与表信息对象关联起来，便于重用
	 */
	public static Map<Class,TableInfo> poClassTableMap = new HashMap<>();
	
	private TableContext() {};
	
	static {
		try {
			//初始化获得表信息
			Connection con = DBManager.getCon();
			DatabaseMetaData dbmd = con.getMetaData(); //获得数据库的所有信息
			
			ResultSet rs = dbmd.getTables("sorm","%","%",new String[] {"TABLE"});
			
			while (rs.next()) {
				String tableName = (String)rs.getString("TABLE_NAME");
				TableInfo ti = new TableInfo(tableName,new ArrayList<ColumnInfo>(),new HashMap<String,ColumnInfo>());
				tables.put(tableName, ti);
				
				ResultSet set = dbmd.getColumns(null, "%", tableName,"%");
				
				while (set.next()) {
					ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0); //0表示是普通键
					ti.getColumn().put(set.getString("COLUMN_NAME"),ci);
				}
				
				
				ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);//查询表中所有主键
				

				while (set2.next()) {
					ColumnInfo ci2 = ti.getColumn().get(set2.getString("COLUMN_NAME"));
					if (ci2 != null) {
					ci2.setKeyType(1); //设为主键类型
					ti.getPriKeys().add(ci2);
					}
					//else System.out.println(set2.getObject("COLUMN_NAME"));
				}
				
				if (ti.getPriKeys().size() > 0) { //取唯一主键，如果是联合主键，则为空
					ti.setPrimeKey(ti.getPriKeys().get(0));
					
				}
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//每次启动时将类更新一遍
		updatePOFiles();
		
		
		//加载Class对象下的po包
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
	
	//加载po包下面的类
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
