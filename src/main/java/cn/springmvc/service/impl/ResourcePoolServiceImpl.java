package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import cn.common.ZsConstant;
import cn.springmvc.model.QueryBackupStorageModel;
import cn.springmvc.model.QueryCpuAndMemoryModel;
import cn.springmvc.model.QueryIpAddressModel;
import cn.springmvc.model.QueryPrimaryStorageModel;
import cn.springmvc.service.ResourcePoolService;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsQueryUtils;

@Component
public class ResourcePoolServiceImpl implements ResourcePoolService {

	@Override
	public List<QueryPrimaryStorageModel> queryPrimaryStorage(String loginUUid) {
		List<QueryPrimaryStorageModel> modelList = new ArrayList<QueryPrimaryStorageModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils
				.getQueryResultJSONArray(
						ZsConstant.API_QUERYPRIMARYSTORAGE_MSG,
						new HashMap<String, JSONArray>(),
						ZsConstant.API_QUERYPRIMARYSTORAGE_REPLY,
						loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				QueryPrimaryStorageModel model = new QueryPrimaryStorageModel();
				model.setAvailableCapacity(String.valueOf(Float.valueOf(json.getString("availableCapacity"))/(1024*1024*1024)));
				model.setName(json.getString("name"));
				model.setState(json.getString("state"));
				model.setStatus(json.getString("status"));
				model.setTotalCapacity(String.valueOf(Float.valueOf(json.getString("totalCapacity"))/(1024*1024*1024)));
				model.setType(json.getString("type"));
				model.setUrl(json.getString("url"));
				model.setUuid(json.getString("uuid"));
				modelList.add(model);
			}
		}

		return modelList;

	}

	@Override
	public List<QueryBackupStorageModel> queryBackupStorage(String loginUUid) {
		List<QueryBackupStorageModel> modelList = new ArrayList<QueryBackupStorageModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYBACKUPSTORAGE_MSG,
				new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYBACKUPSTORAGE_REPLY,
				loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				QueryBackupStorageModel model = new QueryBackupStorageModel();
				model.setAvailableCapacity(json.getString("availableCapacity"));
				model.setName(json.getString("name"));
				model.setState(json.getString("state"));
				model.setStatus(json.getString("status"));
				model.setTotalCapacity(json.getString("totalCapacity"));
				model.setType(json.getString("type"));
				model.setUrl(json.getString("url"));
				model.setUuid(json.getString("uuid"));
				modelList.add(model);
			}
		}

		return modelList;
	}

	@Override
	public QueryCpuAndMemoryModel queryCpuAndMemory(String loginUUid, JSONArray zoneUuids) {
		//封装查询参数获取查询结果
		Map<String, JSONArray> queryConditions = new HashMap<String, JSONArray>();
		queryConditions.put("zoneUuids", zoneUuids);
		JSONObject result = ZsQueryUtils.getQueryResultJson(
				ZsConstant.API_GETCPUMEMORYCAPACITY_MSG, loginUUid,
				queryConditions);
		//封装对应的模型类
		QueryCpuAndMemoryModel model = new QueryCpuAndMemoryModel();
		if (result != null) {
			List<String> keyList = ZsJSonUtils.getJsonKey("result", ZsConstant.API_GETCPUMEMORYCAPACITY_REPLY);
			String creatVmRslt = ZsJSonUtils.getJsonKeyValue(keyList, result);
			// 将字符串中的数组元素转化为json数组
			JSONObject json = JSONObject.fromObject(creatVmRslt);
			// 将解析出来的各个值，封装到model中
			model.setAvailableCpu(json.getString("availableCpu"));
			model.setAvailableMemory(String.valueOf(Float.valueOf(json.getString("availableMemory"))/(1024*1024*1024)));
			model.setTotalCpu(json.getString("totalCpu"));
			model.setTotalMemory(String.valueOf(Float.valueOf(json.getString("totalMemory"))/(1024*1024*1024)));
			model.setSuccess(json.getString("success"));
		}
		// 将对象列表转化为json数组串，传送到前台页面显示
		return model;
	}

	@Override
	public QueryIpAddressModel queryIpAddress(String loginuuid,
			JSONArray zoneUuids) {
		// TODO Auto-generated method stub
		//封装查询参数获取查询结果
				Map<String, JSONArray> queryConditions = new HashMap<String, JSONArray>();
				queryConditions.put("zoneUuids", zoneUuids);
				JSONObject result = ZsQueryUtils.getQueryResultJson(
						ZsConstant.API_GETIPADDRESSCAPACITY_MSG, loginuuid,
						queryConditions);
				//封装对应的模型类
				QueryIpAddressModel queryIpAddressModel = new QueryIpAddressModel();
				if (result != null) {
					List<String> keyList = ZsJSonUtils.getJsonKey("result", ZsConstant.API_GETIPADDRESSCAPACITY_REPLY);
					String creatVmRslt = ZsJSonUtils.getJsonKeyValue(keyList, result);
					// 将字符串中的数组元素转化为json数组
					JSONObject json = JSONObject.fromObject(creatVmRslt);
					// 将解析出来的各个值，封装到model中
					queryIpAddressModel.setAvailableCapacity(json.getString("availableCapacity"));
					queryIpAddressModel.setTotalCapacity(json.getString("totalCapacity"));
					queryIpAddressModel.setSuccess(json.getString("success"));
				}
				// 将对象列表转化为json数组串，传送到前台页面显示
				return queryIpAddressModel;
	}
}
