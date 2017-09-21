package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.QueryInstanceOfferingModel;

public interface InstanceOfferingService {
	/*查询计算规格信息*/
	public List<QueryInstanceOfferingModel> queryInstanceOffering(String loginuuid);
	/*启用计算规格*/
	public String enableInstanceOffering(String uuid);
	/*禁用计算规格*/
	public String disableInstanceOffering(String uuid);
	/*删除计算规格*/
	public String deleteInstanceOffering(String uuid);
}
