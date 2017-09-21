package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.ZsUserModel;

public interface ZsUserService {
	//当创建虚拟机时，给数据库中插入电脑用户名，UUID，默认密码，其中电脑用户名唯一
	public int insertZsUserInfo(ZsUserModel zsUserModel);
	
	public List<ZsUserModel> queryZsUserInfo(ZsUserModel zsUserModel);
	public List<ZsUserModel> checkZsUserInfo(ZsUserModel zsUserModel);
	public void deleteZsUserInfo(ZsUserModel zsUserModel);
	public void resetUserPassword(ZsUserModel zsUserModel);
	public void updateUserName(ZsUserModel zsUserModel);
	public void updateUserPassword(ZsUserModel zsUserModel);

}
