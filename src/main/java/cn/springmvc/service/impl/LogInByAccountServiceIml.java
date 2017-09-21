package cn.springmvc.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.LogInByAccountModel;
import cn.springmvc.service.LogInByAccountService;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsQueryUtils;
import cn.utils.ZsRequestUtils;

@Service
public class LogInByAccountServiceIml implements LogInByAccountService {

	@Override
	public String validateLogin(LogInByAccountModel logInByAccountModel) {
		String loginUUid = null;
		JSONObject jsonResult = ZsJSonUtils.objectToJson(logInByAccountModel);
		if (jsonResult != null) {
			JSONObject jsonParam = new JSONObject();
			jsonParam.put(ZsConstant.API_LOGINBYACCOUNT_MSG, jsonResult);
			JSONObject loginJson = ZsRequestUtils.httpPost(jsonParam);

			// HTTP调用成功时候
			if (loginJson != null) {
				// 获取json对应的key值				
				List<String> keyList = ZsJSonUtils.getJsonKey("result", ZsConstant.API_LOGIN_REPLY, "success");
				
				// API的执行结果成功时候
				if ("true".equals(ZsJSonUtils.getJsonKeyValue(
						keyList, loginJson))) {
					keyList = ZsJSonUtils.getJsonKey("result", ZsConstant.API_LOGIN_REPLY, "inventory", "uuid");
					loginUUid = ZsJSonUtils.getJsonKeyValue(keyList, loginJson);
				}
			}
		}
		return loginUUid;
	}

	@Override
	public boolean updateAccount(String loginUUid, String password) {
		boolean out = false;
		//封装HttpPost请求需要的JSON参数
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("sessionUuid", loginUUid);
		bodyJson.put("password", password);
		JSONObject rootJson = ZsJSonUtils.getRootJson(
				ZsConstant.API_UpdateAccount_MSG, bodyJson, loginUUid);
		//发送HttpPost请求
		JSONObject result = ZsRequestUtils.httpPost(rootJson);
		
		// 通过执行响应的json获取到执行结果的值
		out = ZsQueryUtils.getSuccessKeyValue(result,
				ZsConstant.API_UpdateAccount_MSG,
				ZsConstant.API_UPDATEACCOUNT_EVENT);
		return out;
	}
}
