package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.QueryDiskOfferingModel;
import cn.springmvc.service.DiskOfferingService;
import cn.utils.ZsQueryUtils;

@Service
public class DiskOfferingServiceImpl implements DiskOfferingService {

	@Override
	public List<QueryDiskOfferingModel> queryDiskOffering(String loginUUid) {
		List<QueryDiskOfferingModel> modelList = new ArrayList<QueryDiskOfferingModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYDISKOFFERING_MSG, new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYDISKOFFERING_REPLY, loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				QueryDiskOfferingModel model = new QueryDiskOfferingModel();
				JSONObject json = jsonArray.getJSONObject(i);
				// 将解析出来的各个值，封装到model中
				model.setAllocatorStrategy(json.getString("allocatorStrategy"));
				model.setCreateDate(json.getString("createDate"));
				model.setDiskSize(json.getString("diskSize"));
				model.setLastOpDate(json.getString("lastOpDate"));
				model.setName(json.getString("name"));
				model.setSortKey(json.getString("sortKey"));
				model.setState(json.getString("state"));
				model.setType(json.getString("type"));
				model.setUuid(json.getString("uuid"));
				modelList.add(model);
			}
		}
		
		return modelList;
	}

	@Override
	public String enableDiskOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableDiskOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDiskOffering(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
