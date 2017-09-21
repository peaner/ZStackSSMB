package cn.utils;

/**
 * 动态数据源
 * @author PEANER-Li
 *
 */
public class DataSourceHolder {
	private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

	public static String getDatasources() {
		return dataSources.get();
	}
	
	public static void setDatasources(String dataSource){
		dataSources.set(dataSource);
	}
	
	
}
