package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.CreateL3NetworkModel;
import cn.springmvc.model.QueryL3NetworkModel;
import cn.springmvc.service.L3NetworkService;
import cn.utils.ZsQueryUtils;

@Service
public class L3NetworkServiceImpl implements L3NetworkService {

	@Override
	public List<QueryL3NetworkModel> queryL3Network(String loginUUid) {
		List<QueryL3NetworkModel> modelList = new ArrayList<QueryL3NetworkModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYL3NETWORK_MSG,
				new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYL3NETWORK_REPLY,
				loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				QueryL3NetworkModel model = new QueryL3NetworkModel();
				model.setName(json.getString("name"));
				model.setState(json.getString("state"));
				model.setUuid(json.getString("uuid"));
				modelList.add(model);
			}
		}

		// 将对象列表转化为json数组串，传送到前台页面显示
		return modelList;
	}

	@Override
	public String addIpRange(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteIpRange(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addDns(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDns(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteL3Network(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableL3Network(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableL3Network(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createL3Network(CreateL3NetworkModel createL3NetworkModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
