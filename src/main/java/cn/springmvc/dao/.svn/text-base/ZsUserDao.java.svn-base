package cn.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.springmvc.model.ZsUserModel;

/**
 * 数据库操作接口
 * @author Administrator
 *
 */
@Component
public interface ZsUserDao {
	//插入用户数据
	public int insertZsUserInfo(ZsUserModel zsUserModel);
	
	public List<ZsUserModel> queryZsUserInfo(ZsUserModel zsUserModel);
	//检测用户是否重名
	public List<ZsUserModel> checkZsUserInfo(ZsUserModel zsUserModel);
	
	public void deleteZsUserInfo(ZsUserModel zsUserModel);
	public void resetUserPassword(ZsUserModel zsUserModel);

	public void updateUserName(ZsUserModel zsUserModel);
    //修改终端用户密码
	public void updateUserPassword(ZsUserModel zsUserModel);
}
