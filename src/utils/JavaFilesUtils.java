package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ColumnInfo;
import bean.JavaFieldGetSet;
import bean.TableInfo;
import cores.DBManager;
import cores.MysqlTypeConverter;
import cores.TableContext;
import cores.TypeConvert;

/**
 * ��װ������java�ļ���Դ���룩���õĲ���
 */
public class JavaFilesUtils {
	
	/**
	 * �����ֶ���Ϣ����java������Ϣ����:var username -> private String username�Լ���Ӧ��get��set����
	 * @param column �ֶ���Ϣ
	 * @param converter 
	 * @return Դ��
	 */
	public static JavaFieldGetSet createFieldSrc(ColumnInfo column,TypeConvert converter) {
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		
		String javaFieldType = converter.databaseType2JavaType(column.getDataType());
		
		jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");
		
		//public String getGetInfo() { return column.getName();}
		StringBuilder strOfGet = new StringBuilder(); //�����ַ�����ƴ��
		strOfGet.append("\tpublic " + javaFieldType + " get" + StringUtils.firstChar2UpperCase(column.getName()) + "()" + " {\n\t\treturn " + column.getName() + " ;\n\t}\n");
		jfgs.setGetInfo(strOfGet.toString());
		
		StringBuilder strOfSet = new StringBuilder();
		strOfSet.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(" + javaFieldType + " name )"+ " {\n\t\tthis." + column.getName() + " = name;\n\t}\n");
		jfgs.setSetInfo(strOfSet.toString());
		
		return jfgs;
	}
	
	/**
	 * ���ݱ���Ϣ����java���Դ����
	 * @param ti
	 * @param converter
	 * @return
	 */
	public static String createTableJavaSrc(TableInfo ti,TypeConvert converter) {
		StringBuilder src = new StringBuilder();
		
		Map<String,ColumnInfo> columns = ti.getColumn();
		List<JavaFieldGetSet> javaFields = new ArrayList<>(); //table�������б�
		
		for (ColumnInfo ci : columns.values()) {
			javaFields.add(createFieldSrc(ci,new MysqlTypeConverter()));
		}
		
		//����package���
		src.append("package ");
		src.append(DBManager.getPoPackage() + ";\n\n");
		
		//����import���
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		
		//�������������
		src.append("public class " + StringUtils.firstChar2UpperCase(ti.getName()) + " {\n\n");
		
		//���������б�
		for (JavaFieldGetSet jfgs : javaFields) {
			src.append(jfgs.getFieldInfo());
		}
		src.append("\n\n\n");
		
		//����get�����б�
		for (JavaFieldGetSet jfgs : javaFields) {
			src.append(jfgs.getGetInfo() + "\n");
		}
		
		//set�����б�
		for (JavaFieldGetSet jfgs : javaFields) {
			src.append(jfgs.getSetInfo() + "\n");
		}
		
		src.append("}\n");
		return src.toString();
 	}
	
	/**
	 * ��Դ��д��ָ��java��
	 */
	public static void createJavaPOFile(TableInfo ti,TypeConvert converter) {
		String src = createTableJavaSrc(ti,converter);
		String srcPath = DBManager.getSrcPath();
		//System.out.println(DBManager.getConf().getSrcPath());
		String packagePath = DBManager.getConf().getPoPackage();
		//System.out.println(packagePath);
		File file = new File(srcPath + "\\" + packagePath);
		//File file = new File(srcPath);
		//System.out.println(file.getAbsolutePath());
		
		if (!file.exists()) {
			file.mkdir(); //���Ŀ¼�����ڣ����Զ�����һ��
		}
		
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file.getPath() + "/" + StringUtils.firstChar2UpperCase(ti.getName()) + ".java"));
			bw.write(src);
			//System.out.println(file.getPath() + "\\" + StringUtils.firstChar2UpperCase(ti.getName()) + ".java");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Map<String,TableInfo> map = TableContext.getTableInfos();
		//createJavaPOFile(map.get("employee"),new MysqlTypeConverter());
		System.out.println(new File("po.Employee").exists());
	}
}
