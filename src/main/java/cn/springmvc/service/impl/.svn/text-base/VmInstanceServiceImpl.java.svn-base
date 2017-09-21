package cn.springmvc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.CreateVmInstanceModel;
import cn.springmvc.model.QueryVmInstanceModel;
import cn.springmvc.service.ImageService;
import cn.springmvc.service.VmInstanceService;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsQueryUtils;
import cn.utils.ZsRequestUtils;

@Service
public class VmInstanceServiceImpl implements VmInstanceService {	
	private static Logger logger = LoggerFactory.getLogger(VmInstanceServiceImpl.class); 

	@Autowired
	private ImageService imageService;
	@SuppressWarnings("deprecation")
	@Override
	public List<QueryVmInstanceModel> queryVmStance(String loginUUid) {
		List<QueryVmInstanceModel> modelList = new ArrayList<QueryVmInstanceModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYVMINSTANCE_MSG,
				new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYVMINSTANCE_REPLY, loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				// 获取IP，对json数据进行解析
				JSONArray vmNicsJsonArray = JSONArray.fromObject(json
						.get("vmNics"));
				String ip = "";
				if (vmNicsJsonArray != null && vmNicsJsonArray.size() > 0) {
					JSONObject ipJson = vmNicsJsonArray.getJSONObject(0);
					if (ipJson != null && ipJson.containsKey("ip")) {
						ip = ipJson.getString("ip");
					}
				}
				// 将解析出来的各个值，封装到model中
				QueryVmInstanceModel model = new QueryVmInstanceModel();
				model.setIp(ip);
				model.setName(json.getString("name"));
				model.setPlatform(json.getString("platform"));
				model.setState(json.getString("state"));
				model.setUuid(json.getString("uuid"));
				model.setImageUuid(json.getString("imageUuid"));
				SimpleDateFormat df = new SimpleDateFormat(ZsConstant.DF_YYYY_MM_DD_HH_MM_SS);
				model.setCreateDate(df.format(new Date(json.getString("createDate"))));
				modelList.add(model);
			}
		}

		return modelList;
	}

	@Override
	public boolean createVmStance(String loginuuid,
			CreateVmInstanceModel createVmInstanceModel) {
		boolean out = false;		
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_CREATEVMINSTANCE_MSG,
					ZsJSonUtils.objectToJson(createVmInstanceModel), loginuuid);
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
			// 通过HttpPost请求响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_CREATEVMINSTANCE_MSG,
					ZsConstant.API_CREATEVMINSTANCE_EVENT);			
		} catch (Exception e) {
			logger.error("创建虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public boolean startVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_StartVmInstance_MSG, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_StartVmInstance_MSG,
					ZsConstant.API_STARTVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("启动虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public boolean stopVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_StopVmInstance_MSG, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_StopVmInstance_MSG,
					ZsConstant.API_STOPVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("停止虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public String migrateVmStance(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rebootVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_RebootVmInstance_MSG, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_RebootVmInstance_MSG,
					ZsConstant.API_REBOOTVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("重启虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public String mountVolume(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String umountVolume(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mountL3Network(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String umountL3Network(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroyVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_DestroyVmInstance_Msg, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_DestroyVmInstance_Msg,
					ZsConstant.API_DESTROYVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("删除虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public boolean recoverVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_RecoverVmInstance_Msg, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_RecoverVmInstance_Msg,
					ZsConstant.API_RECOVERVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("恢复虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public boolean expungeVmInstance(String loginUuid, String VmUuid) {
		boolean out = false;
		try {
			// 封装HttpPost请求需要的JSON参数
			JSONObject bodyJson = new JSONObject();
			bodyJson.put("uuid", VmUuid);
			JSONObject rootJson = ZsJSonUtils.getRootJson(
					ZsConstant.API_ExpungeVmInstance_Msg, bodyJson, loginUuid);
	
			// 发送HttpPost请求
			JSONObject result = ZsRequestUtils.httpPost(rootJson);
	
			// 通过执行响应的json获取到执行结果的值
			out = ZsQueryUtils.getSuccessKeyValue(result,
					ZsConstant.API_ExpungeVmInstance_Msg,
					ZsConstant.API_EXPUNGEVMINSTANCE_EVENT);
		} catch (Exception e) {
			logger.error("彻底删除虚拟机异常:" + e.getMessage());
		}
		return out;
	}

	@Override
	public String getVmConsoleAddress(String loginuuid, String VmUuid) {
		// 封装HttpPost请求需要的JSON参数
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("uuid", VmUuid);
		JSONObject rootJson = ZsJSonUtils.getRootJson(
				ZsConstant.API_GETVMCONSOLEADDRESS_MSG, bodyJson, loginuuid);

		// 发送HttpPost请求
		JSONObject result = ZsRequestUtils.httpPost(rootJson);

		// 解析返回结果JSON串
		Map<String, Object> map = new HashMap<String, Object>();
		// 返回虚拟机的端口号
		String message = "";
		if (result != null) {
			List<String> keyList = ZsJSonUtils.getJsonKey("result",	ZsConstant.API_GETVMCONSOLEADDRESS_REPLY);
			String creatVmRslt = ZsJSonUtils.getJsonKeyValue(keyList, result);
			if(creatVmRslt==null){
				return message;
			}
			else{
			     JSONObject json = JSONObject.fromObject(creatVmRslt);
			     String success = json.getString("success");
			     if ("true".equals(success)) {
				    String hostIp = json.getString("hostIp");
				    String port = json.getString("port");
				    String protocol = json.getString("protocol");
				    // 返回字符串的格式 spice://10.177.2.222:5901
				    message = protocol.concat("://").concat(hostIp).concat(":")
						.concat(port);
			     } else {
				    return message;
			     }
			     map.put("querySuccess", "查询虚拟机端口号信息成功！");
		    } 
		}
		else{
			map.put("queryError", "没有虚拟机端口号相关信息！");
		}
		// 将对象列表转化为json数组串，传送到前台页面显示
		return message;
	}

	@SuppressWarnings("deprecation")
	@Override
	public QueryVmInstanceModel queryVmStanceByUuid(String loginuuid,	String vmUuid) {
		//conditions": [{"name": "uuid", "value": "b3e07170b83b497ab32431f05eec3d82", "op": "="}]
		//封装查询参数获取查询结果
        QueryVmInstanceModel model = new QueryVmInstanceModel();
        Map<String, JSONArray> queryConditions = new HashMap<String, JSONArray>();
		JSONArray arrays=new JSONArray();
		JSONObject object=new JSONObject();
		object.put("name", "uuid");
		object.put("value", vmUuid);
		object.put("op", "=");
		arrays.add(0, object);
		queryConditions.put("conditions", arrays);
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYVMINSTANCE_MSG,
				queryConditions,
				ZsConstant.API_QUERYVMINSTANCE_REPLY, loginuuid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				// 获取IP，对json数据进行解析
				JSONArray vmNicsJsonArray = JSONArray.fromObject(json
						.get("vmNics"));
				String ip = "";
				if (vmNicsJsonArray != null && vmNicsJsonArray.size() > 0) {
					JSONObject ipJson = vmNicsJsonArray.getJSONObject(0);
					if (ipJson != null && ipJson.containsKey("ip")) {
						ip = ipJson.getString("ip");
					}
				}
				// 将解析出来的各个值，封装到model中
				model.setIndex(i);
				model.setIp(ip);
				model.setName(json.getString("name"));
				model.setPlatform(json.getString("platform"));
				model.setState(json.getString("state"));
				model.setUuid(json.getString("uuid"));
				model.setImageUuid(json.getString("imageUuid"));
				SimpleDateFormat df = new SimpleDateFormat(ZsConstant.DF_YYYY_MM_DD_HH_MM_SS);
				model.setCreateDate(df.format(new Date(json.getString("createDate"))));
				
			}
		}
		return model;		
	}

	@Override
	public boolean updateVmInstanceName(String loginUuid, String vmUuid,
			String vmName) {
		boolean out = false;
		// 封装HttpPost请求需要的JSON参数
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("uuid", vmUuid);
		bodyJson.put("name", vmName);
		JSONObject rootJson = ZsJSonUtils.getRootJson(
				ZsConstant.API_UPDATEVMINSTANCE_MSG, bodyJson, loginUuid);

		// 发送HttpPost请求
		JSONObject result = ZsRequestUtils.httpPost(rootJson);

		// 通过执行响应的json获取到执行结果的值
		out = ZsQueryUtils.getSuccessKeyValue(result,
				ZsConstant.API_UPDATEVMINSTANCE_MSG,
				ZsConstant.API_UPDATEVMINSTANCE_EVENT);
		return out;
	}
}
