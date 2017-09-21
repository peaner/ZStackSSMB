package cn.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.common.ZsConstant;
import cn.springmvc.model.QueryImageModel;
import cn.springmvc.service.ImageService;
import cn.utils.ZsQueryUtils;

@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public List<QueryImageModel> queryImage(String loginUUid) {
		List<QueryImageModel> modelList = new ArrayList<QueryImageModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QueryImage_MSG, new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYIMAGE_REPLY, loginUUid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				QueryImageModel modle = new QueryImageModel();
				JSONObject image = jsonArray.getJSONObject(i);
				if (image.getString("mediaType").equals("RootVolumeTemplate") && image.getString("format").equals("qcow2") 
						&& image.getString("state").equals("Enabled") && image.getString("status").equals("Ready")) {
					modle.setName(image.getString("name"));
					modle.setUuid(image.getString("uuid"));
					modle.setMediaType(image.getString("mediaType"));
					modle.setFormat(image.getString("format"));
					modle.setSize(image.getString("size"));
					modelList.add(modle);
				}			
			}
		}
		// 将对象列表转化为json数组串，传送到前台页面显示
		return modelList;
	}
	
	@Override
	public List<QueryImageModel> queryImageName(String loginUuid) {
		List<QueryImageModel> modelList = new ArrayList<QueryImageModel>();
		// 获取查询到的JSONArray对象
		JSONArray jsonArray = ZsQueryUtils.getQueryResultJSONArray(
				ZsConstant.API_QueryImage_MSG, new HashMap<String, JSONArray>(),
				ZsConstant.API_QUERYIMAGE_REPLY, loginUuid);
		// 循环JSONArray封装需要的模型类
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				QueryImageModel modle = new QueryImageModel();
				JSONObject image = jsonArray.getJSONObject(i);			
				modle.setName(image.getString("name"));
				modle.setUuid(image.getString("uuid"));
				modle.setMediaType(image.getString("mediaType"));
				modle.setFormat(image.getString("format"));
				modle.setSize(image.getString("size"));
				modelList.add(modle);						
			}
		}
		// 将对象列表转化为json数组串，传送到前台页面显示
		return modelList;
	}

	@Override
	public String enableImage(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableImage(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteImage(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
