package bean;

/**
 * ����������Ϣ
 * @author xqx
 *
 */
public class Configuration {
	private String driver;
	private String url;
	private String name;
	private String pwd;
	private String usingDB;
	
	/*
	 * ��Ŀ��Դ��·��
	 */
	private String srcPath;
	
	/*
	 * ɨ������java��İ�(po����˼��:Persistence object �ɳ־û�����)
	 */
	private String poPackage;
	
	private String queryClass; //��Ŀʹ�õĲ�ѯ��ĵ�ַ
	
	private int poolMaxSize;
	
	private int poolMinSize;

	public int getPoolMaxSize() {
		return poolMaxSize;
	}

	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}

	public int getPoolMinSize() {
		return poolMinSize;
	}

	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}

	public String getQueryClass() {
		return queryClass;
	}

	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsingDB() {
		return usingDB;
	}

	public void setUsingDB(String usingDB) {
		this.usingDB = usingDB;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getPoPackage() {
		return poPackage;
	}

	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}
	
	
}
