package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.ZsUserDao;
import cn.springmvc.model.ZsUserModel;
import cn.springmvc.service.ZsUserService;

@Service
public class ZsUserServiceImpl implements ZsUserService{
	
	@Autowired
	private ZsUserDao zsUserDao;

	@Override
	public int insertZsUserInfo(ZsUserModel zsUserModel) {
		return zsUserDao.insertZsUserInfo(zsUserModel);
	}

	@Override
	public List<ZsUserModel> queryZsUserInfo(ZsUserModel zsUserModel) {
		return zsUserDao.queryZsUserInfo(zsUserModel);
	}

	@Override
	public void deleteZsUserInfo(ZsUserModel zsUserModel) {
		// TODO Auto-generated method stub
		zsUserDao.deleteZsUserInfo(zsUserModel);
	}

	@Override
	public List<ZsUserModel> checkZsUserInfo(ZsUserModel zsUserModel) {
		// TODO Auto-generated method stub
		return zsUserDao.checkZsUserInfo(zsUserModel);
	}

	@Override
	public void resetUserPassword(ZsUserModel zsUserModel) {
		// TODO Auto-generated method stub
		zsUserDao.resetUserPassword(zsUserModel);
	}

	@Override
	public void updateUserName(ZsUserModel zsUserModel) {
		// TODO Auto-generated method stub
		zsUserDao.updateUserName(zsUserModel);
	}

	@Override
	public void updateUserPassword(ZsUserModel zsUserModel) {
		// TODO Auto-generated method stub
		zsUserDao.updateUserPassword(zsUserModel);
	}
}
