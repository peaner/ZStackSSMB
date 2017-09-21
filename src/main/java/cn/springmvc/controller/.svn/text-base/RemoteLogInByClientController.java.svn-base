package cn.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.LogInByAccountModel;
import cn.springmvc.model.ZsUserModel;
import cn.springmvc.service.LogInByAccountService;
import cn.springmvc.service.VmInstanceService;
import cn.springmvc.service.ZsUserService;
import cn.utils.ZsEncryptUtils;

@Controller
@RequestMapping("/remoteService")
public class RemoteLogInByClientController {

	@Autowired
	private LogInByAccountService logInByAccountService;
	@Autowired
	private ZsUserService zsUserService;
	@Autowired
	private VmInstanceService vmInstanceService;
	/**
     * 远程登录验证
     * @param request
     */
	@ResponseBody
    @RequestMapping("/validateRemoteLogin")
    public Map<String, Object> validateRemoteLogin(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("执行了登录操作！");
        String accountName=request.getParameter("accountName");
        String password=request.getParameter("password");
        System.out.println(accountName+"  "+password);
        //首先登录zstack的后台，获得登录uuid，方便后续操作zstack其他API
        LogInByAccountModel logInByAccountModel = new LogInByAccountModel();
        logInByAccountModel.setAccountName("admin");
        logInByAccountModel.setPassword(ZsEncryptUtils.SHA256("password"));
        //根据账号查询用户名是否存在
        String loginuuid="";
        try {
            loginuuid = logInByAccountService.validateLogin(logInByAccountModel);
		} catch (Exception e) {
			//登录异常，zstack系统问题
			resultMap.put("zstackLoginMsg", false);
			return resultMap;
		}      
        if (loginuuid != null){
        	 HttpSession session = request.getSession();  
             session.setAttribute("accountName", logInByAccountModel.getAccountName());
             session.setAttribute("uuid", loginuuid);
             //登录成功
             resultMap.put("zstackLoginMsg", true);
        }else {
        	//"zstack用户名不存在或者密码错误, 请检查重新输入"
        	resultMap.put("zstackLoginMsg", false);
        	return resultMap;
        }
        ZsUserModel zsUserModel=new ZsUserModel();
        zsUserModel.setUser(accountName);
        zsUserModel.setPassword(password);
        List<ZsUserModel> zsUserModels=null;
        try {
        	zsUserModels=zsUserService.queryZsUserInfo(zsUserModel);
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("loginResult", false);
			return resultMap;
		}        
        if (zsUserModels.size()>0) {
			System.out.println("执行了终端登录操作！");
			resultMap.put("loginResult", true);
			String vmUuid=zsUserModels.get(0).getUuid();
			String port=vmInstanceService.getVmConsoleAddress(loginuuid, vmUuid);
		    if ("".equals(port)) {
				//System.out.println("虚拟机必须处于Running状态才能查询到端口号");
				//resultMap.put("queryPortResult", false);
				boolean flag=vmInstanceService.startVmInstance(loginuuid, vmUuid);
				if(flag){
					port=vmInstanceService.getVmConsoleAddress(loginuuid, vmUuid);
					//System.out.println("虚拟机的端口号为："+port);
					resultMap.put("queryPortResult", port);
				}
				else{
					//System.out.println("虚拟机启动失败！");
					resultMap.put("queryPortResult", false);
					return resultMap;
				}
			}
		    else {
				//System.out.println("虚拟机的端口号为："+port);
				resultMap.put("queryPortResult", port);
			}
        }
        else{
        	//System.out.println("数据库查询失败！");
        	resultMap.put("loginResult", false);       	
        	return resultMap;
        }
        return resultMap;
    }
	
	/**
     * 远程登录验证
     * @param request
     */
	@ResponseBody
    @RequestMapping("/RemoteUpdatePassword")
    public Map<String, Object> RemoteUpdatePassword(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("执行了远程修改用户密码操作！");
        String accountName=request.getParameter("accountName");
        String oldPassword=request.getParameter("oldPassword");
        String currentPassword=request.getParameter("currentPassword");
        System.out.println(accountName+"  "+oldPassword+"  "+currentPassword);
        //首先登录zstack的后台，获得登录uuid，方便后续操作zstack其他API
        LogInByAccountModel logInByAccountModel = new LogInByAccountModel();
        logInByAccountModel.setAccountName("admin");
        logInByAccountModel.setPassword(ZsEncryptUtils.SHA256("password"));
        //根据账号查询用户名是否存在
        String loginuuid="";
        try {
            loginuuid = logInByAccountService.validateLogin(logInByAccountModel);
		} catch (Exception e) {
			//登录异常，zstack系统问题
			resultMap.put("zstackLoginMsg", false);
			return resultMap;
		}      
        if (loginuuid != null){
        	 HttpSession session = request.getSession();  
             session.setAttribute("accountName", logInByAccountModel.getAccountName());
             session.setAttribute("uuid", loginuuid);
             //登录成功
             resultMap.put("zstackLoginMsg", true);
        }else {
        	//"zstack用户名不存在或者密码错误, 请检查重新输入"
        	resultMap.put("zstackLoginMsg", false);
        	return resultMap;
        }
        ZsUserModel zsUserModel=new ZsUserModel();
        zsUserModel.setUser(accountName);
        zsUserModel.setPassword(oldPassword);
        List<ZsUserModel> zsUserModels=zsUserService.queryZsUserInfo(zsUserModel);
        if (zsUserModels.size()>0) {
			//表明数据库中有此人，可以进行修改密码
        	zsUserModel=zsUserModels.get(0);
        	zsUserModel.setPassword(currentPassword);
        	try {
        		zsUserService.updateUserPassword(zsUserModel);
			} catch (Exception e) {
				// TODO: handle exception
				resultMap.put("result", "201");
				return resultMap;
			}
        	resultMap.put("result", "200");
		}else {
			resultMap.put("result", "404");
		}       
        return resultMap;
    }
}