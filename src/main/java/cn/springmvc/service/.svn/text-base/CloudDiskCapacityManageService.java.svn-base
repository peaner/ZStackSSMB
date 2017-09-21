package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.CloudDiskInfo;
import cn.springmvc.model.CloudDiskUser;


/**
 * @author 胡志强
 * 2017年5月23日
 */
public interface CloudDiskCapacityManageService {
    /**
     * 查看云盘容量信息 
     * @param 
     * @return
     */
	 List<CloudDiskInfo> queryClouddiskCapacityInfo(CloudDiskUser cloudDiskUser);
	 
	 /**
	  * 申请扩容
	 * @param cloudDiskInfo
	 * @return int
	 */
	int applyCapacity(CloudDiskInfo cloudDiskInfo);

	/**
	 * 更新云盘信息
	 * @param cloudDiskInfo
	 */
	void cloudDiskInfoUpdate(CloudDiskInfo cloudDiskInfo);

	/**
	 * 删除云盘信息
	 * @param cloudDiskInfo
	 */
	void cloudDiskInfoDelete(CloudDiskInfo cloudDiskInfo);
	 
}
