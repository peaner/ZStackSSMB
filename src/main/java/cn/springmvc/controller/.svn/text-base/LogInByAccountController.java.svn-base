package cn.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.LogInByAccountModel;
import cn.springmvc.service.LogInByAccountService;
import cn.utils.ZsEncryptUtils;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsRequestUtils;

@Controller
@RequestMapping("/login")
public class LogInByAccountController {

	@Autowired
	private LogInByAccountService logInByAccountService;
	
    /**
     * 登陆表单校验
     * @param request
     */
	@ResponseBody
    @RequestMapping("/validateLogin")
    public Map<String, Object> login(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
        //获取form表单数据
        String loginObj = ZsRequestUtils.getRequestKeyValue(request, "loginObj");
        //JSON字符串序列化成JSON对象
        JSONObject loginJosn = ZsJSonUtils.objectToJson(loginObj);
        LogInByAccountModel logInByAccountModel = new LogInByAccountModel();
        logInByAccountModel.setAccountName(loginJosn.getString("accountName"));
        logInByAccountModel.setPassword(ZsEncryptUtils.SHA256(loginJosn.getString("password")));
        //根据账号查询用户名是否存在
        String loginUUid = logInByAccountService.validateLogin(logInByAccountModel);
        if (loginUUid != null){
        	 HttpSession session = request.getSession();  
             session.setAttribute("accountName", logInByAccountModel.getAccountName());
             session.setAttribute("uuid", loginUUid);
             resultMap.put("errMsg", "");
        }else {
        	resultMap.put("errMsg", "用户名不存在或者密码错误, 请检查重新输入");
        }
        
        return resultMap;
    }
	
	/**
	 * @param request
	 * @return  修改密码成功与否
	 */
	@ResponseBody
    @RequestMapping("/updatePassword")
    public Map<String, Object> updatePassword(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
        //获取form表单数据
        String updatePasswordObj = ZsRequestUtils.getRequestKeyValue(request, "updatePasswordJson");
        //JSON字符串序列化成JSON对象
        JSONObject updatePasswordJosn = ZsJSonUtils.objectToJson(updatePasswordObj);
        String changePassword = ZsEncryptUtils.SHA256(updatePasswordJosn.getString("changePassword"));
        HttpSession session = request.getSession();  
	    String loginUUid = session.getAttribute("uuid").toString(); 
	    if(logInByAccountService.updateAccount(loginUUid, changePassword)){
	    	resultMap.put("success", "true");
	    }
	    else{
	    	resultMap.put("success", "false");
	    }
        
        return resultMap;
    }
}