package cn.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.springmvc.model.CloudDiskInfo;
import cn.springmvc.model.UserDiskInfo;

@Component
public interface CloudDiskInfoMapper {
    int insert(CloudDiskInfo record);

    int insertSelective(CloudDiskInfo record);
    
    void updatecloudDiskInfo(CloudDiskInfo record);
    
    void deleteCloudDiskInfo(CloudDiskInfo record);

	List<UserDiskInfo> queryInfoByPage(Map<String, Object> mapParam);
}