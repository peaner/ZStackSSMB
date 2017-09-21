package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.QueryInstanceOfferingModel;
import cn.springmvc.service.InstanceOfferingService;
import cn.utils.ZsQueryUtils;

@Service
public class InstanceOfferingServiceImpl implements InstanceOfferingService {

	@Override
	public List<QueryInstanceOfferingModel> queryInstanceOffering(String loginUUid) {
		List<QueryInstanceOfferingModel> modelList = new ArrayList<QueryInstanceOfferingModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils
				.getQueryResultJSONArray(
						ZsConstant.API_QueryInstance_MSG,
						new HashMap<String, JSONArray>(),
						ZsConstant.API_QUERYINSTANCEOFFERING_REPLY,
						loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				QueryInstanceOfferingModel modle = new QueryInstanceOfferingModel();
				JSONObject instance = jsonArray.getJSONObject(i);
				modle.setUuid(instance.getString("uuid"));
				modle.setName(instance.getString("name"));
				modle.setCpuNum(instance.getString("cpuNum"));
				modle.setMemorySize(instance.getString("memorySize"));
				modelList.add(modle);
			}
		}

		// 将对象列表转化为json数组串，传送到前台页面显示
		return modelList;
	}

	@Override
	public String enableInstanceOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableInstanceOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteInstanceOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
