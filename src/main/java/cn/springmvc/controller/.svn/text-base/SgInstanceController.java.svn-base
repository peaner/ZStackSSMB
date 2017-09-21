package cn.springmvc.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.CloudDiskInfo;
import cn.springmvc.model.CloudDiskUser;
import cn.springmvc.model.UserDiskInfo;
import cn.springmvc.service.CloudDiskCapacityManageService;
import cn.springmvc.service.UserManageService;
import cn.springmvc.service.ZsUserService;
import cn.utils.ZsJSonUtils;
import cn.utils.ZsRequestUtils;

/**
 * 存储管理
 * @author PEANER-Li
 *
 */
@Controller
@RequestMapping("/storage")
public class SgInstanceController {

	private static Logger logger = LoggerFactory.getLogger(SgInstanceController.class);
	@Autowired
	private CloudDiskCapacityManageService cloudDiskCMService;
	@Autowired
	private UserManageService userManageService;
	@Autowired
	private ZsUserService zsUserService;

	/**
	 * 检测数据
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkSg")
	public Map<String, Object> checkSgValue(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取form表单中的数据
		String sgObj = ZsRequestUtils.getRequestKeyValue(request, "sgLogin");
		CloudDiskUser cloudDiskUser = new CloudDiskUser();
		cloudDiskUser.setUserLoginID(sgObj);
		List<CloudDiskUser> list = userManageService.userMqueryCdUser(cloudDiskUser);
		if(list.size() > 0){
			resultMap.put("result", 1);
		}else{
			resultMap.put("result", 0);
		}
		return resultMap;
	}

	/**
	 * 创建存储用户
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/createSg")
	public Map<String, Object> createSgInstance(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String creatSgObj = ZsRequestUtils.getRequestKeyValue(request, "creatSgJson");
		JSONObject creatSgJSON = ZsJSonUtils.objectToJson(creatSgObj);
		boolean createSg = userManageService.userMinsertZsUserInfo(creatSgJSON);
		if(createSg){
			CloudDiskUser cloudDiskUser = new CloudDiskUser();
			cloudDiskUser.setUserLoginID(creatSgJSON.getString("sgLogin"));
			List<CloudDiskUser> userList = userManageService.userMqueryCdUser(cloudDiskUser);
			map.put("row", userList);
		}
		map.put("result", createSg);
		return map;
	}

	/**
	 * 云盘用户信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sgInfoInit")
	public Map<String,Object> sgInfoInit(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapParam = new HashMap<String, Object>();
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
		mapParam.put("start", offset);
		mapParam.put("end", Long.valueOf(limit));
		if("userLoginID".equals(sortName) || "totalSize".equals(sortName) || "usedSize".equals(sortName)){
			mapParam.put("sortName", "-"+sortName+" ");
		}else{
			mapParam.put("sortName", sortName+" ");
		}
		mapParam.put("sortOrder", sortOrder);
		mapParam.put("search", search);
		List<UserDiskInfo> userList = userManageService.userMqueryByPage(mapParam);
		List<UserDiskInfo> user =userManageService.userMCount(mapParam);
		int total = user.size();
		for(int i = 0 ; i < userList.size() ; i++){
			String size = userList.get(i).getTotalSize();
			String used = userList.get(i).getUsedSize();
			Long s = Long.valueOf(size);
			Long u = null;
			try {
				if("0".equals(used) || used == null){
					userList.get(i).setUsedSize("0KB");
				}else{
					DecimalFormat df = new DecimalFormat("#.00");
					u = Long.valueOf(used);
					if( u < 1024*1024){
						used = String.valueOf(df.format(u/(float)1024));
						userList.get(i).setUsedSize(used+"KB");
					}else if(u >= 1024*1024 && u < 1024*1024*1024){
						used = String.valueOf(df.format(u/(float)1024/(float)1024));
						userList.get(i).setUsedSize(used+"MB");
					}else if(u > 1024*1024*1024){
						used = String.valueOf(df.format(u/(float)(1024*1024*1024)));
						userList.get(i).setUsedSize(used+"G");
					}else{
						userList.get(i).setUsedSize("未知");
					}
				}
				size = String.valueOf(s/1024/1024/1024);
			} catch (Exception e) {
				logger.error("存储分配大小出现异常",e.getMessage());
			}
			userList.get(i).setTotalSize(size+"G");
		}
		
		map.put("total", total);
		map.put("rows", userList);
		return map;
	} 

	/**
	 * 重置云盘密码  默认888888
	 * @param userID 用户id 唯一标识
	 * @return
	 */
	@ResponseBody
	@RequestMapping("resetSgPassword")
	public Map<String,Object> resetSgPassword(@RequestParam(value = "userID[]") String[] userIDs){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean reset = userManageService.userMupdatePassword(userIDs);
		map.put("result", reset);
		return map;
	}
	
	
	/**
	 * 删除存储空间
	 * @param uuids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteSgInstance")
	public Map<String,Object> deleteSgInstance(@RequestParam(value = "userID[]") String[] userIDs){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean deleteResult = userManageService.userMdeleteInfo(userIDs);
		map.put("result", deleteResult);
		return map;
	}
	
	/**
	 * 修改存储大小
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateSgNum")
	public Map<String,Object> updateSgNum(@RequestParam(value = "userID") String userID,
			@RequestParam(value = "sgNum") String totalSize){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userID!=null && totalSize!=null){
			CloudDiskInfo cloudDiskInfo = new CloudDiskInfo();
			cloudDiskInfo.setUserid(userID);
			Long size = Long.valueOf(totalSize);
			try {
				totalSize = String.valueOf(size*1024*1024*1024);
			} catch (Exception e) {
				logger.error("存储分配大小出现异常",e.getMessage());
			}
			cloudDiskInfo.setTotalsize(totalSize);
			cloudDiskCMService.cloudDiskInfoUpdate(cloudDiskInfo);
			map.put("result", true);
		}else{
			map.put("result", false);
		}
		return map;
	}
}
