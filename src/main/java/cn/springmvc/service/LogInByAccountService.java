package cn.springmvc.service;

import cn.springmvc.model.LogInByAccountModel;

public interface LogInByAccountService {
	//登陆验证
	public String validateLogin(LogInByAccountModel logInByAccountModel);
	//修改密码
	public boolean updateAccount(String loginUUid, String password); 
}
