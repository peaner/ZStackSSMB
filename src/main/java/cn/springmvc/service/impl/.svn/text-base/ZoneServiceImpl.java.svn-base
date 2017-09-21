package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.common.ZsConstant;
import cn.springmvc.model.QueryZoneModel;
import cn.springmvc.service.ZoneService;
import cn.utils.ZsQueryUtils;

/**
 * @author hzq
 * @date 2017年4月21日
 */
@Service
public class ZoneServiceImpl implements ZoneService {
	
	@Override
	public List<QueryZoneModel> queryZone(String loginUUid) {
		List<QueryZoneModel> modelList = new ArrayList<QueryZoneModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QUERYZONE_MSG,
				new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYZONE_REPLY, loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				QueryZoneModel model = new QueryZoneModel();
				model.setUuid(json.getString("uuid"));
				model.setName(json.getString("name"));
				model.setState(json.getString("state"));				
				model.setType(json.getString("type"));
				modelList.add(model);
			}
		}

		return modelList;
	}

}
