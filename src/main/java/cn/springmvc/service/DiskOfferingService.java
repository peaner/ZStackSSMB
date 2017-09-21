package cn.springmvc.service;
import java.util.List;

import cn.springmvc.model.QueryDiskOfferingModel;

public interface DiskOfferingService {
	/*查询磁盘规格*/
	public List<QueryDiskOfferingModel> queryDiskOffering(String loginuuid);
	/*启用磁盘规格*/
	public String enableDiskOffering(String uuid);
	/*禁用磁盘规格*/
	public String disableDiskOffering(String uuid);
	/*删除磁盘规格*/
	public String deleteDiskOffering(String uuid);
}
