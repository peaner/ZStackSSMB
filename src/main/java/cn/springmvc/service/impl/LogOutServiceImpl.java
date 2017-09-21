package cn.springmvc.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.service.LogOutService;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsRequestUtils;

@Service
public class LogOutServiceImpl implements LogOutService{

	@Override
	public boolean logOut(String loginUUid) {
		boolean logOut = false;
		//封装HttpPost请求需要的JSON参数
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("sessionUuid", loginUUid);		
		JSONObject rootJson = ZsJSonUtils.getRootJson(
				ZsConstant.API_LOGOUT_MSG, bodyJson, loginUUid);
		
		//发送HttpPost请求
	    JSONObject result = ZsRequestUtils.httpPost(rootJson);
	    
		// 通过执行响应的json获取到执行结果的值
	    if(result != null){
	    	if(String.valueOf(ZsConstant.manageType.Done).equals(result.getString("state"))){
				List<String> keyList = ZsJSonUtils.getJsonKey("result", ZsConstant.API_LOGOUT_REPLY, "success");
				String s = ZsJSonUtils.getJsonKeyValue(keyList, result);
				if("true".equals(s)){
					 logOut = true;
				}
	    	}
	    }
		
		return logOut;
	}
	
}
