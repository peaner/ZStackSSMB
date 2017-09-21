package cn.springmvc.service.impl2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.CloudDiskInfoMapper;
import cn.springmvc.model.CloudDiskInfo;
import cn.springmvc.model.CloudDiskUser;
import cn.springmvc.service.CloudDiskCapacityManageService;
@Service
public class CloudDiskCapacityManageServiceImpl implements
		CloudDiskCapacityManageService {
	
	@Autowired
	private CloudDiskInfoMapper cloudDiskInfoMapper;

	@Override
	public List<CloudDiskInfo> queryClouddiskCapacityInfo(CloudDiskUser cloudDiskUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int applyCapacity(CloudDiskInfo cloudDiskInfo) {
		return cloudDiskInfoMapper.insertSelective(cloudDiskInfo);
	}

	@Override
	public void cloudDiskInfoUpdate(CloudDiskInfo cloudDiskInfo) {
		cloudDiskInfoMapper.updatecloudDiskInfo(cloudDiskInfo);
	}

	@Override
	public void cloudDiskInfoDelete(CloudDiskInfo cloudDiskInfo) {
		cloudDiskInfoMapper.deleteCloudDiskInfo(cloudDiskInfo);
	}

}
