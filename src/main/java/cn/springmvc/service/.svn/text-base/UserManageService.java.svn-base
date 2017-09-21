package cn.springmvc.service;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.springmvc.model.CloudDiskUser;
import cn.springmvc.model.UserDiskInfo;


/**
 * @author 胡志强
 * 2017年5月23日
 */
public interface UserManageService {
    /**
     * 云盘用户登录验证信息 
     * @param cloudDiskUser
     * @return
     */
	List<CloudDiskUser> userMLoginCheck(CloudDiskUser cloudDiskUser);
	/**
	 * 修改用户密码
	 * @param cloudDiskUser
	 * @return
	 */
	boolean userMupdatePassword(String[] userIDs);	
	/**
	 * 退出系统
	 * @param cloudDiskUser
	 * @return
	 */	
	void userMquitSystem(CloudDiskUser cloudDiskUser);
	
	/**
	 * 添加云盘用户
	 * @param cloudDiskUser
	 */
	boolean userMinsertZsUserInfo(JSONObject creatSgJSON);
	
	/**
	 * 查询云盘用户
	 * @param cloudDiskUser
	 * @return
	 */
	List<CloudDiskUser> userMqueryCdUser(CloudDiskUser cloudDiskUser);
	
	/**
	 * 查询用户综合信息
	 * @param userDiskInfo
	 * @return
	 */
	List<UserDiskInfo> userMquery(UserDiskInfo userDiskInfo);
	
	/**
	 * 删除用户信息
	 * @param cloudDiskUser
	 */
	boolean userMdeleteInfo(String[] userIDs);
	
	/**
	 * 分页查询用户信息
	 * @param mapParam
	 * @return
	 */
	List<UserDiskInfo> userMqueryByPage(Map<String, Object> mapParam);
	
	
	/**
	 * 查询用户数量
	 * @return
	 */
	List<UserDiskInfo> userMCount(Map<String, Object> mapParam);
	
}
