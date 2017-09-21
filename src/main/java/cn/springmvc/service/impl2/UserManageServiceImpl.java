package cn.springmvc.service.impl2;



import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.common.ZsConstant;
import cn.springmvc.dao.CloudDiskInfoMapper;
import cn.springmvc.dao.CloudDiskUserMapper;
import cn.springmvc.dao.UserDiskInfoMapper;
import cn.springmvc.model.CloudDiskInfo;
import cn.springmvc.model.CloudDiskUser;
import cn.springmvc.model.UserDiskInfo;
import cn.springmvc.service.UserManageService;
import cn.utils.DateUtils;
import cn.utils.ZsCreatUuid;
import cn.utils.ZsEncryptUtils;
@Service
public class UserManageServiceImpl implements UserManageService {
	
	private static Logger logger = LoggerFactory.getLogger(UserManageServiceImpl.class);
    @Autowired
	private CloudDiskUserMapper cloudDiskUserMapper;
    @Autowired
    private UserDiskInfoMapper userDiskInfoMapper;
    @Autowired
    private CloudDiskInfoMapper cloudDiskInfoMapper;
	
    @Override
	public List<CloudDiskUser> userMLoginCheck(CloudDiskUser cloudDiskUser) {
		return cloudDiskUserMapper.queryCloudDiskUserInfo(cloudDiskUser);
	}

    /**
     * 修改密码
     */
    @Transactional
	@Override
	public boolean userMupdatePassword(String[] userIDs) {
    	boolean userUpdateResult = false;
    	try {
			if (userIDs != null) {
				for (String userID : userIDs) {
					CloudDiskUser cloudDiskUser = new CloudDiskUser();
					cloudDiskUser.setUserid(userID);
					cloudDiskUser.setPassword(ZsEncryptUtils.SHA256(ZsConstant.DEFAULT_PASSWORD));
					CloudDiskInfo cloudDiskInfo = new CloudDiskInfo();
					cloudDiskInfo.setUpdatedate(DateUtils.getSystemTime());
					cloudDiskInfo.setUserid(userID);
					//重置密码
					cloudDiskUserMapper.updateCloudDiskUserInfo(cloudDiskUser);
					//更改日期
					cloudDiskInfoMapper.updatecloudDiskInfo(cloudDiskInfo);
				}
			}
			userUpdateResult = true;
		} catch (Exception e) {
			logger.error("修改密码时数据库操作发生异常：" + e.getMessage());
		}
		return userUpdateResult;
	}

	@Override
	public void userMquitSystem(CloudDiskUser cloudDiskUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CloudDiskUser> userMqueryCdUser(CloudDiskUser cloudDiskUser) {
		return cloudDiskUserMapper.queryCloudDiskUserInfo(cloudDiskUser);
	}

	@Override
	public List<UserDiskInfo> userMquery(UserDiskInfo userDiskInfo) {
		return userDiskInfoMapper.queryUserDiskInfo(userDiskInfo);
	}

	/**
	 * 删除云盘
	 */
	@Transactional
	@Override
	public boolean userMdeleteInfo(String[] userIDs) {
		boolean userMdeleteResult = false;
		try {
			if (userIDs != null) {
				for (String userID : userIDs) {
					CloudDiskUser cloudDiskUser = new CloudDiskUser();
					CloudDiskInfo cloudDiskInfo = new CloudDiskInfo();
					cloudDiskUser.setUserid(userID);
					cloudDiskInfo.setUserid(userID);
					//删除用户表里面的信息
					cloudDiskUserMapper.deleteCloudDiskUserInfo(cloudDiskUser);
					//删除云盘表中的信息
					cloudDiskInfoMapper.deleteCloudDiskInfo(cloudDiskInfo);
				}
			}
			userMdeleteResult = true;
		} catch (Exception e) {
			logger.error("删除云盘用户时数据库操作发生异常：" + e.getMessage());
			throw new RuntimeException();
		}
		return userMdeleteResult;
	}

	/**
	 * 创建云盘用户
	 */
	@Transactional
	@Override
	public boolean userMinsertZsUserInfo(JSONObject creatSgJSON) {
		boolean userInsertResult = false;
		try {
			//用户表信息
			CloudDiskUser cloudDiskUser = new CloudDiskUser();
			CloudDiskInfo cloudDiskInfo = new CloudDiskInfo();
			cloudDiskUser.setUserid(ZsCreatUuid.getUuid());
			cloudDiskUser.setUsername(creatSgJSON.getString("sgName"));
			cloudDiskUser.setUserLoginID(creatSgJSON.getString("sgLogin"));
			//密码
			cloudDiskUser.setPassword(ZsEncryptUtils.SHA256(ZsConstant.DEFAULT_PASSWORD));
			//存储分配表信息
			cloudDiskInfo.setUpdatedate(DateUtils.getSystemTime());
			String sgNum = creatSgJSON.getString("sgNum");
			Long a = Long.valueOf(sgNum);
			try {
				sgNum = String.valueOf(a*1024*1024*1024);
			} catch (Exception e) {
			    logger.error("存储分配大小出现异常",e.getMessage());
			}
			cloudDiskInfo.setTotalsize(sgNum);
			cloudDiskInfo.setUsedsize("0");
			cloudDiskInfo.setUserid(cloudDiskUser.getUserid());
			cloudDiskUser.setOperator(ZsConstant.OPERATE_NAME);
			cloudDiskUser.setCreatedate(DateUtils.getSystemTime());
			cloudDiskUserMapper.insertCloudDiskUserInfo(cloudDiskUser);
			cloudDiskInfoMapper.insertSelective(cloudDiskInfo);
			userInsertResult = true;
		} catch (Exception e) {
			logger.error("创建云盘用户时数据库操作发生异常：" + e.getMessage());
			throw new RuntimeException();
		}	
		return userInsertResult;
	}

	
	/**
	 * 分页查询用户信息
	 */
	@Override
	public List<UserDiskInfo> userMqueryByPage(Map<String, Object> mapParam) {
		return userDiskInfoMapper.queryInfoByPage(mapParam);
	}

	/**
	 * 查询用户数量
	 */
	@Override
	public List<UserDiskInfo> userMCount(Map<String, Object> mapParam) {
		return userDiskInfoMapper.queryCount(mapParam);
	}


}
