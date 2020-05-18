package cores;

/*
 * 设计一个可以实现对象与SQL自动映射的框架，但是整体用法比Hibernate简单。
 * 增删改查等功能都可以将对象对应成SQL语句，不必再手写
 * 核心架构：
 * 		Query接口：负责查询
 * 		QueryFactory类：根据配置信息创建Query类
 * 		TypeConvert接口：负责类型转换
 * 		TableContext类：负责获取数据库中表结构和类结构的关系，并根据表结构生成类结构。
 * 		DBManager类：根据配置信息，实现对象的管理
 * 工具类：
 * 		JDBCUtils:封装常用JDBC操作 		StringUtils:封装常用字符串操作
 * 		JavaFilesUtils:封装文件操作		ReflectUtils:封装反射操作
 * 核心bean:
 * 		ColumnInfo:封装表中一个字段的信息（字段类型、字段名、键类型)
 * 		Configuration:封装配置文件信息
 * 		TableInfo:封装一张表的信息
 */
public class SormStart {

}
