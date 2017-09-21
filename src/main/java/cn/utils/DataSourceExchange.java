package cn.utils;

import org.aspectj.lang.JoinPoint;

import cn.common.ZsConstant;

/**
 * 拦截器方法
 * @author PEANER-Li
 *
 */
public class DataSourceExchange {
	
	/**
	 * 执行前选择数据源
	 * @param point
	 */
	public void before(JoinPoint point){
		//获取目标对象的类型
		Class<?> aClass = point.getTarget().getClass();
		
		//获取包名用于区分不同数据源 这里需要再约定一下
		String whichDataSource = aClass.getName().substring(21,aClass.getName().lastIndexOf("."));
		if("impl".equals(whichDataSource)){
			DataSourceHolder.setDatasources(ZsConstant.DATASOURCE_ONE);
		}else{
			DataSourceHolder.setDatasources(ZsConstant.DATASOURCE_TWO);
		}
		
	}
	
	/**
	 * 执行后将数据源置为空
	 */
	public void after(){
		DataSourceHolder.setDatasources(null);
	}

}
