package cn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.common.ZsCommon;
import cn.common.ZsConstant;

public class ZsQueryUtils{
	private static Logger logger = LoggerFactory.getLogger(ZsQueryUtils.class); 
	private static final String JSON_KEY_STATE = "state";	
	private static final String JSON_KEY_RESULT = "result";
	private static final String JSON_KEY_INVENTORIES = "inventories";
	private static final String JSON_KEY_CONDITIONS = "conditions";
	
	/**
	 * 	循环查询API执行状态
	 * @param uuid    执行API返回的UUID，用于查询用
	 * @param apiId   执行API的名称：例如org.zstack.header.vm.APICreateVmInstanceMsg
	 */
	public static JSONObject queryUntilDone(String uuid, String apiId) {
		JSONObject queryJson = null;
		while(queryJson == null){
			queryJson = ZsRequestUtils.httpGet(ZsConstant.ZSTACK_API_GET_URL + uuid);
			String state = ZsJSonUtils.getJsonKeyValue(JSON_KEY_STATE, queryJson);
			//如果状态不是Done继续循环查询
			if(state == null || !String.valueOf(ZsConstant.manageType.Done).equals(state)){
				ZsCommon.apiUuidAndQueryJsonResult.put(apiId + uuid, queryJson);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					logger.error("循环查询API执行状态出现异常：" + e.getMessage());
					queryJson = null;
					break;
				}
				queryJson = null;
			}
		}
		if(queryJson != null){
			logger.info("循环查询API执行状态结果：" + ZsJSonUtils.jsonStringformat(queryJson.toString()));	
		}		
		
		return queryJson;
	}
	
	/**
	 * 获取JSONArray类型的查询结果(本方法获取的是inventories的返回，如果是inventory的另外方法实现)
	 * @param queryApiKey 查询API，例如：org.zstack.header.configuration.APIQueryDiskOfferingMsg
	 * @param queryConditions  查询条件  如果无需条件传入null, 查询全部传入new HashMap<String, JSONArray>
	 * @param replyApiKey 查询相应API， 例如：org.zstack.header.network.l3.APIQueryL3NetworkReply
	 * @param loginUUid 登录用户UUID
	 * @return
	 */	 
	public static JSONArray getQueryResultJSONArray(String queryApiKey, Map<String, JSONArray> queryConditions, String replyApiKey, String loginUUid) {
		JSONArray jsonArray = new JSONArray();
		
		// 获取查询的JSON结果
		JSONObject result = ZsQueryUtils.getQueryResultJson(queryApiKey, loginUUid, queryConditions);

		// 结果不为空的时候
		if (result != null) {
			// 获取json对应的key值
			List<String> keyList = ZsJSonUtils.getJsonKey(JSON_KEY_RESULT, replyApiKey, JSON_KEY_INVENTORIES);

			// 获取key值对应的value值
			String keyValue = ZsJSonUtils.getJsonKeyValue(keyList, result);

			// 获取value值的JSONArray对象			
			if (keyValue != null) {
				jsonArray = JSONArray.fromObject(keyValue);
			}
		}

		return jsonArray;
	}
	
	/**
	 * 查询处理
	 * @param apiKey 请求的Zstack对应API名称，例如org.zstack.header.configuration.APIQueryDiskOfferingMsg
	 * @param loginUUid 用于封装公共的API参数 其中都是登录的UUID
	 * @param queryConditions 查询条件 如果无需条件传入null即可
	 * @return
	 */
	public static JSONObject getQueryResultJson(String apiKey, String loginUUid, Map<String, JSONArray> queryConditions) {
	    JSONObject bodyJson = new JSONObject();
	    if(queryConditions != null){
	    	if(queryConditions.size() == 0){
		    	 bodyJson.put(JSON_KEY_CONDITIONS, new ArrayList<String>());
		    } else {
		    	bodyJson = JSONObject.fromObject(queryConditions);
		    }
	    }	    	   
	    JSONObject rootJson = ZsJSonUtils.getRootJson(apiKey, bodyJson, loginUUid);
	    JSONObject result = ZsRequestUtils.httpPost(rootJson);
	    
		return result;
	}
	
	/**
	 * 通过执行响应的json获取到执行结果的值(试用于创建虚拟机返回UUID后需要轮询查询的情况)
	 * @param resultJSON 执行操作同步返回的正在处理的json
	 * @param requestApiKey 请求API，例如：org.zstack.header.vm.APIStartVmInstanceMsg
	 * @param responseApiKey 响应API，例如：org.zstack.header.vm.APIStartVmInstanceEvent
	 * @return 成功为true，失败为false
	 */
	public static boolean getSuccessKeyValue(JSONObject resultJSON, String requestApiKey, String responseApiKey) {
		boolean result = false;
		if(resultJSON != null){
			String uuid = ZsJSonUtils.getJsonKeyValue("uuid", resultJSON);
			JSONObject apiJson = ZsQueryUtils.queryUntilDone(uuid, requestApiKey);
			List<String> keyList = ZsJSonUtils.getJsonKey("result", responseApiKey, "success");
			String sucValue = ZsJSonUtils.getJsonKeyValue(keyList, apiJson);
			if ("true".equals(sucValue)) {
				result = true;
			}
		}

		return result;
	}
}
