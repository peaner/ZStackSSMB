package cn.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.common.ZsConstant;
import cn.springmvc.model.CreateVmInstanceModel;
import cn.springmvc.model.QueryImageModel;
import cn.springmvc.model.QueryL3NetworkModel;
import cn.springmvc.model.QueryVmInstanceModel;
import cn.springmvc.model.ZsUserModel;
import cn.springmvc.service.ImageService;
import cn.springmvc.service.L3NetworkService;
import cn.springmvc.service.ResourcePoolService;
import cn.springmvc.service.VmInstanceService;
import cn.springmvc.service.ZsUserService;
import cn.utils.ZsCreatUuid;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsRequestUtils;

@Controller
@RequestMapping("/vm")
public class VmInstanceController {
	@Autowired
	private ZsUserService zsUserService;
	@Autowired
	private VmInstanceService vmInstanceService;
	@Autowired
	private L3NetworkService l3NetworkService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ResourcePoolService resourcePoolService;

	/**
	 * 创建虚拟机
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/createVm")
	public Map<String, Object> createVmInstance(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取form表单数据
		String loginUuid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		String creatVmObj = ZsRequestUtils.getRequestKeyValue(request, "creatVmJson");
		JSONObject creatVmJSON = ZsJSonUtils.objectToJson(creatVmObj);

		// 获取创建虚拟机模型
		CreateVmInstanceModel createVmInstanceModel = new CreateVmInstanceModel();
		// 虚拟机名称
		createVmInstanceModel.setName(creatVmJSON.getString("name"));
		// 虚拟机计算性能
		createVmInstanceModel.setInstanceOfferingUuid(creatVmJSON.getString("instanceOfferingUuid"));
		// 虚拟机操作系统
		createVmInstanceModel.setImageUuid(creatVmJSON.getString("imageUuid"));
		// 虚拟机三层网络
		List<QueryL3NetworkModel> queryL3NetworkModelList = l3NetworkService.queryL3Network(loginUuid);
		String[] l3NetworkUuids = new String[queryL3NetworkModelList.size()];
		for (int j = 0; j < queryL3NetworkModelList.size(); j++) {
			l3NetworkUuids[j] = queryL3NetworkModelList.get(j).getUuid();
		}
		createVmInstanceModel.setL3NetworkUuids(l3NetworkUuids);
		// 虚拟机默认三层网络, 如果l3NetworkUuids>1，任意选择一个就行
		createVmInstanceModel
				.setDefaultL3NetworkUuid(l3NetworkUuids[(int) (Math.random() * l3NetworkUuids.length)]);
		// 设置资源UUID
		String uuid = ZsCreatUuid.getUuid();
		createVmInstanceModel.setResourceUuid(uuid);

		boolean controlResult = vmInstanceService.createVmStance(loginUuid, createVmInstanceModel);
		Map<String, Object> map = new HashMap<String, Object>();
		QueryVmInstanceModel model = new QueryVmInstanceModel();
		// 创建成功时候插入数据库
		if (controlResult) {
			ZsUserModel zsUserModel = new ZsUserModel();
			zsUserModel.setUuid(uuid);
			zsUserModel.setUser(creatVmJSON.getString("name"));
			zsUserModel.setPassword(ZsConstant.VM_DEFAULT_PASSWORD);
			SimpleDateFormat df = new SimpleDateFormat(ZsConstant.DF_YYYY_MM_DD_HH_MM_SS);
			zsUserModel.setDate(df.format(new Date()));
			zsUserService.insertZsUserInfo(zsUserModel);
			//获取新建电脑信息，便于前台进行更新
			model = vmInstanceService.queryVmStanceByUuid(loginUuid, uuid);
			List<QueryImageModel> queryImageModels = imageService.queryImage(loginUuid);
			for(QueryImageModel queryImageModel : queryImageModels){
				String imageUuid = model.getImageUuid();
				if(imageUuid != null && imageUuid.equals(queryImageModel.getUuid())){
					model.setImageName(queryImageModel.getName());
					break;
				}
			}
		}
		
		map.put("row", model);
		map.put("result", controlResult);
		return map;
	}
	
	/**
	 * 虚拟机操作处理
	 * 
	 * @param args
	 */
	@ResponseBody
	@RequestMapping(value = "/vmControl")
	public Map<String, Object> vmControl(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String loginUuid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		String cpUuid = ZsRequestUtils.getRequestKeyValue(request, "cpUuid");
		String index = ZsRequestUtils.getRequestKeyValue(request, "index");
		String controlType = ZsRequestUtils.getRequestKeyValue(request, "controlType");
		String isExpunge = null;
		int switchCondition = 0;
		if (controlType != null) {
			switchCondition = Integer.parseInt(controlType);
		}
		boolean controlResult = false;
		switch (switchCondition) {
		case 1:		
			controlResult = vmInstanceService.startVmInstance(loginUuid, cpUuid);
			break;
		case 2:
			controlResult = vmInstanceService.stopVmInstance(loginUuid, cpUuid);
			break;
		case 3:
			controlResult = vmInstanceService.rebootVmInstance(loginUuid, cpUuid);
			break;
		case 4:
			controlResult = vmInstanceService.destroyVmInstance(loginUuid, cpUuid);
			break;
		case 5:
			controlResult = vmInstanceService.recoverVmInstance(loginUuid, cpUuid);
			break;
		case 6:
			controlResult = vmInstanceService.expungeVmInstance(loginUuid, cpUuid);
			if (controlResult) {
				ZsUserModel zsUserModel = new ZsUserModel();
				zsUserModel.setUuid(cpUuid);
				zsUserService.deleteZsUserInfo(zsUserModel);
				isExpunge = "OK";
			}
			break;
		default :
			break;
		}
		//获取操作电脑后对应的电脑信息，便于前台进行更新
		QueryVmInstanceModel model = vmInstanceService.queryVmStanceByUuid(loginUuid, cpUuid);
		List<QueryImageModel> queryImageModels = imageService.queryImageName(loginUuid);
		for(QueryImageModel queryImageModel : queryImageModels){
			String imageUuid = model.getImageUuid();
			if(imageUuid != null && imageUuid.equals(queryImageModel.getUuid())){
				model.setImageName(queryImageModel.getName());
				break;
			}
		}	
		map.put("row", model);
		map.put("index", index);
		map.put("expunge", isExpunge);
		map.put("result", controlResult);
		return map;
	}

	/**
	 * 重置终端用户登录密码，默认888888
	 * 
	 * @param args
	 */
	@ResponseBody
	@RequestMapping(value = "/resetUserPassword")
	public Map<String, Object> resetUserPassword(
			@RequestParam(value = "uuids[]") String[] uuids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (uuids != null) {
			for (String uuid : uuids) {
				try {
					ZsUserModel zsUserModel = new ZsUserModel();
					zsUserModel.setUuid(uuid);
					zsUserService.resetUserPassword(zsUserModel);
				} catch (Exception e) {
					map.put("result", false);
				}
			}
			if (!map.containsKey("result")) {
				map.put("result", true);
			}
		}

		return map;
	}
	/**
	 * 修改虚拟机用户名
	 * 
	 * @param args
	 */
	@ResponseBody
	@RequestMapping(value = "/updateVmInstanceName")
	public Map<String, Object> updateVmInstanceName(HttpServletRequest request,
			@RequestParam(value = "vmName",required=true) String vmName,
			@RequestParam(value = "vmUuid",required=true) String vmUuid) {
		String loginUuid=ZsRequestUtils.getSessionKeyValue(request, "uuid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		if (loginUuid !=null) {
			boolean result=vmInstanceService.updateVmInstanceName(loginUuid, vmUuid, vmName);
			if (result) {
				ZsUserModel zsUserModel=new ZsUserModel();
				zsUserModel.setUuid(vmUuid);
				zsUserModel.setUser(vmName);
				zsUserService.updateUserName(zsUserModel);
				zsUserService.resetUserPassword(zsUserModel);
				map.put("result", true);
			}
		}
		
		return map;
	}
}
