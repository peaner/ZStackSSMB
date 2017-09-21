package cn.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态调用数据库连接池
 * @author PEANER-Li
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDatasources();
	}
}
