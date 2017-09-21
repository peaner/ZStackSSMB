package cn.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.service.LogOutService;
import cn.utils.ZsRequestUtils;

@Controller
@RequestMapping("/logout")
public class LogOutController {
	@Autowired
	private LogOutService logOutService;
	
	@ResponseBody
    @RequestMapping("/validate")
    public String loginOut(HttpServletRequest request){
	    String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
	    if(loginUUid != null && logOutService.logOut(loginUUid)){
			HttpSession session = request.getSession();
			session.invalidate();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";

			return basePath;
	    }
	    return null;
    }
	
	@ResponseBody
    @RequestMapping("/closeWindow")
    public void closeWindow(HttpServletRequest request){
	    String loginUUid = ZsRequestUtils.getSessionKeyValue(request, "uuid");
	    if(loginUUid != null && logOutService.logOut(loginUUid)){
			HttpSession session = request.getSession();
			session.removeAttribute("uuid");
	    }
    }
}
