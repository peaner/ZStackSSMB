package cn.springmvc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.common.ZsCommon;
import cn.springmvc.model.QueryCpuAndMemoryModel;
import cn.springmvc.model.QueryDiskOfferingModel;
import cn.springmvc.model.QueryImageModel;
import cn.springmvc.model.QueryInstanceOfferingModel;
import cn.springmvc.model.QueryIpAddressModel;
import cn.springmvc.model.QueryPrimaryStorageModel;
import cn.springmvc.model.QueryVmInstanceModel;
import cn.springmvc.model.QueryZoneModel;
import cn.springmvc.model.ZsUserModel;
import cn.springmvc.service.DiskOfferingService;
import cn.springmvc.service.ImageService;
import cn.springmvc.service.InstanceOfferingService;
import cn.springmvc.service.ResourcePoolService;
import cn.springmvc.service.VmInstanceService;
import cn.springmvc.service.ZoneService;
import cn.springmvc.service.ZsUserService;
import cn.utils.ZsRequestUtils;

/**
 * 下拉框初始化处理
 * 
 * @author hzq
 * 
 */
@Controller
@RequestMapping("/init")
public class MainPageInitController {
	@Autowired
	private InstanceOfferingService instanceOfferingService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private DiskOfferingService diskOfferingService;
	@Autowired
	private VmInstanceService vmInstanceService;
	@Autowired
	private ResourcePoolService resourcePoolService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private ZsUserService zsUserService;

	/**
	 * 跳转主界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/main")
	public ModelAndView login(HttpServletRequest request, ModelAndView model) {
		model.setViewName("main");
		return model;

	}

	/**
	 * 计算机性能下拉框值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/calPerformanceInit")
	public List<QueryInstanceOfferingModel> getCalPerformanceValue(
			HttpServletRequest request, HttpServletResponse response) {
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryInstanceOfferingModel> modelList = new ArrayList<QueryInstanceOfferingModel>();
		if (loginUUid != null) {
			modelList = instanceOfferingService.queryInstanceOffering(loginUUid);
		}
		return modelList;
	}

	/**
	 * 操作系统下拉框值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/operSystemInit")
	public List<QueryImageModel> getOperSystemValue(HttpServletRequest request,
			HttpServletResponse response) {
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryImageModel> queryImageModelList = new ArrayList<QueryImageModel>();
		if (loginUUid != null) {
			queryImageModelList = imageService.queryImage(loginUUid);
		}
		return queryImageModelList;
	}
	
	/**
	 * 数据检测
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkValues")
	public Map<String, Object> checkValues(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取form表单中的数据
		String creatVmObj = ZsRequestUtils.getRequestKeyValue(request, "cpName");
		
		// 查询创建虚拟机用户模型
		ZsUserModel zsUserModel = new ZsUserModel();
		zsUserModel.setUser(creatVmObj.toString());
		List<ZsUserModel> list1 = zsUserService.checkZsUserInfo(zsUserModel);
		zsUserModel.setUser(creatVmObj.toString()+"-1");
		List<ZsUserModel> list2 = zsUserService.checkZsUserInfo(zsUserModel);
		if (list1.size() == 0 && list2.size() == 0) {
			resultMap.put("result", 0);// 可使用！
		} else {
			resultMap.put("result", 1);// 当前名称已使用！
		}
		return resultMap;
	}

	/**
	 * 存储容量下拉框值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/storCapacityInit")
	public List<QueryDiskOfferingModel> getStorCapacityValue(
			HttpServletRequest request, HttpServletResponse response) {
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryDiskOfferingModel> modelList = new ArrayList<QueryDiskOfferingModel>();
		if (loginUUid != null) {
			modelList = diskOfferingService.queryDiskOffering(loginUUid);
		}
		return modelList;
	}

	/**
	 * 电脑情况查询 分页时BootStrap table 向后端传递两个分页字段：limit， offset
	 * ,前者表示每页的个数，默认为10个，后者表示分页时数据的偏移量。 而搜索时则向后端传递的是search字段。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/computerInfoInit")
	public Map<String, Object> getComputerInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//排序列名
		String sortName = ZsRequestUtils.getRequestKeyValue(request, "sort");
		//排序顺序
		String sortOrder = ZsRequestUtils.getRequestKeyValue(request, "order");
		// 表示每页的个数
		String limit = ZsRequestUtils.getRequestKeyValue(request, "limit");
		// 表示分页时数据的偏移量
		String offset = ZsRequestUtils.getRequestKeyValue(request, "offset");
		// 搜索字段
		String search = ZsRequestUtils.getRequestKeyValue(request, "search");
		search = ZsRequestUtils.formatBootstrapTableEncoding(search);
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryVmInstanceModel> vmInstanceModelResult = new ArrayList<QueryVmInstanceModel>();
		if (loginUUid != null) {
			vmInstanceModelResult = vmInstanceService.queryVmStance(loginUUid);
			List<QueryImageModel> queryImageModels=imageService.queryImageName(loginUUid);
			for(QueryVmInstanceModel queryVmInstanceModel:vmInstanceModelResult){
				String imageUuid=queryVmInstanceModel.getImageUuid();
				for (int i = 0; i < queryImageModels.size(); i++) {
					if (imageUuid.equals(queryImageModels.get(i).getUuid())) {
						queryVmInstanceModel.setImageName(queryImageModels.get(i).getName());
						break;
					}
				}
				
			}
		}
		int total = vmInstanceModelResult.size();
		//注意顺序一定不能错误，必须先搜索处理，然后分页处理，最后才是排序处理
		if (total > 0) {
			// 搜索处理
			getSeachPageData(search, map, vmInstanceModelResult);
			//如果进行了搜索处理，重新设置数据的总数量
			if(search != null && !"".equals(search.trim())){
				total = vmInstanceModelResult.size();
			}
			// 分页处理
			getCurrentPageData(limit, offset, map, vmInstanceModelResult);
			// 排序处理
			sortCurrentPageData(sortName, sortOrder, vmInstanceModelResult);
		}		

		//设置bootstap table必须要用到的两个字段total和rows，否则表格无法显示数据
		map.put("total", total);
		map.put("rows", vmInstanceModelResult);		
		return map;
	}
	
	/**
	 * 搜索处理：搜索出符合条件的所有记录
	 * @param search
	 * @param map
	 * @param queryVmInstanceModelList
	 */
	private void getSeachPageData(String search,
			Map<String, Object> map,
			List<QueryVmInstanceModel> queryVmInstanceModelList) {
		//需要搜索的情况处理
		if (search != null && !"".equals(search.trim())) {
			List<QueryVmInstanceModel> selectVmInstanceModel = new ArrayList<QueryVmInstanceModel>();
			if (queryVmInstanceModelList != null) {
				//获取当前页需要显示的数据集合
				for (QueryVmInstanceModel model : queryVmInstanceModelList) {
					if (model.getIp().contains(search)
							|| model.getName().contains(search)
							|| model.getPlatform().contains(search)
							|| model.getState().contains(search)) {
						selectVmInstanceModel.add(model);
					}
				}
				queryVmInstanceModelList.clear();
				queryVmInstanceModelList.addAll(selectVmInstanceModel);
			}
		}		
	}

	/**
	 * 分页处理：返回分页后要显示的数据
	 * @param request
	 * @param map
	 * @param jsonArray
	 */
	private void getCurrentPageData(String limit,
			String offset, Map<String, Object> map,
			List<QueryVmInstanceModel> queryVmInstanceModelList) {
		//需要分页的情况处理
		if (limit != null  && !"".equals(limit.trim()) 
				&& offset != null && !"".equals(offset.trim())) {
			List<QueryVmInstanceModel> pageVmInstanceModel = new ArrayList<QueryVmInstanceModel>();
			int pageDataNum = Integer.parseInt(limit);
			int index = Integer.parseInt(offset);
			//获取当前页需要显示的数据集合
			if (queryVmInstanceModelList.size() > index) {
				for (int i = index; i < queryVmInstanceModelList.size(); i++) {
					if (pageVmInstanceModel.size() < pageDataNum) {
						pageVmInstanceModel.add(queryVmInstanceModelList.get(i));
					} else {
						break;
					}
				}
				queryVmInstanceModelList.clear();
				queryVmInstanceModelList.addAll(pageVmInstanceModel);
			}
		}		
	}
	
	/**
	 * 排序处理，排序当前的数据
	 * @param sortName
	 * @param sortOrder
	 * @param vmInstanceModelList
	 */
	private void sortCurrentPageData(String sortName,
			String sortOrder, List<QueryVmInstanceModel> vmInstanceModelList) {	
		if(sortName != null && !"".equals(sortName.trim()) 
				&& sortOrder != null && !"".equals(sortOrder.trim())){
			ZsCommon.selectVmInstanceModelColumn = sortName;
			//升序处理
			if("asc".equals(sortOrder)){
				Collections.sort(vmInstanceModelList, Collections.reverseOrder());				
			//降序处理
			}else if("desc".equals(sortOrder)){
				Collections.sort(vmInstanceModelList);
			}						
		}
		//重新设置记录里面的index，便于后续指定更新具体行			
		for(int i=0; i < vmInstanceModelList.size(); i++){
			vmInstanceModelList.get(i).setIndex(i);
		}
	}



	/**
	 * 磁盘利用率饼形图相关值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/capacityChartInit")
	public List<QueryPrimaryStorageModel> getCpuChartValue(
			HttpServletRequest request, HttpServletResponse response) {
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryPrimaryStorageModel> modelList = new ArrayList<QueryPrimaryStorageModel>();
		if (loginUUid != null) {
			modelList = resourcePoolService.queryPrimaryStorage(loginUUid);
		}
		return modelList;
	}

	/**
	 * CPU、内存利用率饼形图相关值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cpuAndMemoryChartInit")
	public QueryCpuAndMemoryModel getcpuAndMemoryChartValue(
			HttpServletRequest request, HttpServletResponse response) {
		String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		List<QueryZoneModel> queryZoneModelList = new ArrayList<QueryZoneModel>();
		if (loginUUid != null) {
			queryZoneModelList = zoneService.queryZone(loginUUid);
		}

		// 获取zone的uuid
		JSONArray zoneUuids = new JSONArray();
		if (queryZoneModelList != null) {
			for (QueryZoneModel queryZoneModel : queryZoneModelList) {
				zoneUuids.add(queryZoneModel.getUuid());
			}
		}
		QueryCpuAndMemoryModel queryCpuAndMemoryModel = new QueryCpuAndMemoryModel();
		// 查询资源池中的CPU和内存信息
		if (loginUUid != null && zoneUuids.size() > 0) {
			queryCpuAndMemoryModel = resourcePoolService.queryCpuAndMemory(
					loginUUid, zoneUuids);
		}
		return queryCpuAndMemoryModel;
	}
	
	/**
	 *  创建虚拟机数量的上限
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createVmNumber")
	public Map<String, Object> createVmNumber(HttpServletRequest request) {
		String loginUuid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
		String imageUuid = ZsRequestUtils.getRequestKeyValue(request, "imageUuid");
		String instanceOfferingUuid = ZsRequestUtils.getRequestKeyValue(request, "instanceOfferingUuid");
		Map<String, Object> map = new HashMap<String, Object>();
		
		//查询镜像信息
		List<QueryImageModel> queryImageModelList = imageService.queryImage(loginUuid);
		String imageSize = "", primarySzie = "",cpuNum="",memorySize="";
		for (int i = 0; i < queryImageModelList.size(); i++) {
			if (imageUuid != null && imageUuid.equals(queryImageModelList.get(i).getUuid())) {
				imageSize = queryImageModelList.get(i).getSize();
				break;
			}
		}
		
		//查询资源池中的磁盘信息，对应界面的磁盘利用率
		List<QueryPrimaryStorageModel> queryPrimaryStorageModelList = resourcePoolService.queryPrimaryStorage(loginUuid);
		if(queryPrimaryStorageModelList != null && queryPrimaryStorageModelList.size() > 0){
			primarySzie = queryPrimaryStorageModelList.get(0).getAvailableCapacity();
		}
		List<QueryZoneModel> queryZoneModelList = new ArrayList<QueryZoneModel>();
		queryZoneModelList = zoneService.queryZone(loginUuid);
		// 获取zone的uuid
		JSONArray zoneUuids = new JSONArray();
		if (queryZoneModelList != null) {
			for (QueryZoneModel queryZoneModel : queryZoneModelList) {
				zoneUuids.add(queryZoneModel.getUuid());
			}
		}
		QueryIpAddressModel queryIpAddressModel=new QueryIpAddressModel();
		//查询资源池中IP地址容量信息
		if (loginUuid != null && zoneUuids.size() > 0) {
			queryIpAddressModel = resourcePoolService.queryIpAddress(loginUuid, zoneUuids);
		}
		QueryCpuAndMemoryModel queryCpuAndMemoryModel = new QueryCpuAndMemoryModel();
		// 查询资源池中的CPU和内存信息
		if (loginUuid != null && zoneUuids.size() > 0) {
			queryCpuAndMemoryModel = resourcePoolService.queryCpuAndMemory(
					loginUuid, zoneUuids);
		}
        //查询CPU和内存信息
        List<QueryInstanceOfferingModel> queryInstanceOfferingModels= instanceOfferingService.queryInstanceOffering(loginUuid);
        for(int i=0;i<queryInstanceOfferingModels.size();i++){
			if (instanceOfferingUuid !=null && instanceOfferingUuid.equals(queryInstanceOfferingModels.get(i).getUuid())) {
				//"cpuNum": 20,"cpuSpeed": 1,
                 cpuNum=queryInstanceOfferingModels.get(i).getCpuNum();
                 memorySize=queryInstanceOfferingModels.get(i).getMemorySize();
                 break;
			}
		}
        try {
			Long numberImage = (long) ((Float.valueOf(primarySzie)*1024*1024*1024) / Long.valueOf(imageSize));
			Long numberCpu=Long.valueOf(queryCpuAndMemoryModel.getAvailableCpu())/Long.valueOf(cpuNum);
			Long numberMemory=(long)((Float.valueOf(queryCpuAndMemoryModel.getAvailableMemory())*1024*1024*1024)/Long.valueOf(memorySize));
			Long numberIpAddress=Long.valueOf(queryIpAddressModel.getAvailableCapacity());
			Long temp1=Math.min(numberCpu.longValue(), numberImage.longValue());
			Long temp2=Math.min(numberMemory.longValue(), numberIpAddress.longValue());
			Long result=Math.min(temp1, temp2);
			map.put("result", result);
		} catch (Exception e) {
			map.put("result", "数值运算异常");
		}
		return map;
	}
}
