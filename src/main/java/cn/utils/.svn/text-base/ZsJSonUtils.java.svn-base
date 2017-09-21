package cn.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.springmvc.model.CommonSessionModel;

public class ZsJSonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ZsJSonUtils.class); 

	/**
	 * 实体类型转换成JSON对象
	 * @param obj
	 * @return
	 */
	public static JSONObject objectToJson(Object obj) {
		JSONObject jsonResult = null;
		try {
			jsonResult = JSONObject.fromObject(obj);
		} catch (Exception e) {
			logger.error("转换的对象不是JSON的格式");
		}
		return jsonResult;
	}
	
	/**
	 * 通过制定的keyList获取json对应的value值
	 * @param keyList
	 * @param jsonObject
	 * @return
	 */
	public static String getJsonKeyValue(List<String> keyList, JSONObject jsonObject) {
		String keyValue = null;	
		
		if(keyList.size() > 0 && jsonObject != null){
			JSONObject jsonTemp = jsonObject;
			for(int i = 0; i < keyList.size(); i++) {
				if(i != 0){
					jsonTemp = ZsJSonUtils.objectToJson(keyValue);
				}
				//通过制定的key获取json对应的value值
				keyValue = getJsonKeyValue(keyList.get(i), jsonTemp);
				
				//指定的key获取不到对应的value值，返回null
				if(jsonTemp == null) {					
					keyValue = null;
					break;
				}
			}
		}
		
		return keyValue;
	}
	
	/**
	 * 通过制定的key获取json对应的value值
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static String getJsonKeyValue(String key, JSONObject jsonObject) {
		String keyValue = null;	
		
		if(jsonObject != null && jsonObject.containsKey(key)){
			keyValue = jsonObject.getString(key);
		} else {
			//指定的key获取不到对应的value值，返回null
			logger.error("指定的key[" + key + "]获取不到对应的value值");
		}
		
		return keyValue;
	}
	
	/**
	 * 封装HttpPost请求需要的JSON参数
	 * @param apiKey 请求的Zstack对应API名称，例如org.zstack.header.identity.APIUpdateAccountMsg
	 * @param bodyJson Zstack对应API的实体参数
	 * @param loginUUid 用于封装公共的API参数 其中都是登录的UUID
	 * @return 封装好的HttpPost请求需要的JSON参数
	 */
	public static JSONObject getRootJson(String apiKey, JSONObject bodyJson, String loginUUid) {
		JSONObject rootJson = new JSONObject();	
		CommonSessionModel commonSessionModel = new CommonSessionModel();
		if(loginUUid != null){
			commonSessionModel.setUuid(loginUUid);
			commonSessionModel.setCallid("");
			if(bodyJson != null){
				bodyJson.put("session", objectToJson(commonSessionModel));
				rootJson.put(apiKey, bodyJson);
			}			
		}		
		return rootJson;
	}
	
	/**
	 * 获取json对应的key值，从第一层开始到需要指定获取值的那一层的key值
	 * @param jsonKey 可以是多个参数
	 * @return
	 */
	public static List<String> getJsonKey(String... jsonKey) {
		List<String> keyList = new ArrayList<String>();
		if(jsonKey != null) {
			for(String key : jsonKey){
				keyList.add(key);
			}
		}
		
		return keyList;
	}

	/**
	 * json 字符串格式化
	 * @param jsonStr
	 * @return
	 */
	public static String jsonStringformat(String jsonStr) {
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		jsonStr = jsonStr.replace("\\", "");
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
				jsonForMatStr.append(getLevelStr(level));
			}
			switch (c) {
			case '{':
			case '[':
				jsonForMatStr.append(c + "\n");
				level++;
				break;
			case ',':
				jsonForMatStr.append(c + "\n");
				break;
			case '}':
			case ']':
				jsonForMatStr.append("\n");
				level--;
				jsonForMatStr.append(getLevelStr(level));
				jsonForMatStr.append(c);
				break;
			default:
				jsonForMatStr.append(c);
				break;
			}
		}

		return jsonForMatStr.toString();

	}

	/**
	 * 设置空格格数（可以为\t，空格等等）
	 * @param level
	 * @return
	 */
	private static String getLevelStr(int level) {
		StringBuffer levelStr = new StringBuffer();
		for (int levelI = 0; levelI < level; levelI++) {
			levelStr.append("  ");
		}
		return levelStr.toString();
	}
}
