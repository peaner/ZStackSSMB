<%@ page language="java" import="java.util.*,cn.utils.ZsGetHostIp" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	String hostIp = ZsGetHostIp.getHostIp();
	String settingUrl = request.getScheme() + "://" + hostIp;
%>
 
<!DOCTYPE html >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.54, user-scalable=0" />
<title>智云科技融合云系统</title>
<link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap-table.css">
<link rel="stylesheet" href="<%=basePath%>css/icons.css">
<style type="text/css">
	.mouse-on-title {
	    color: #66ffff;
	}
	.label-state {
	  display: inline;
	  padding: .2em .6em .3em;
	  font-size: 75%;
	  font-weight: bold;
	  line-height: 1;
	  color: #ffffff;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: baseline;
	  border-radius: .25em;
	}	
</style>

</head>
<body style="background-color:#B9DEFF;min-width: 767px;padding-right:0">
	<div class="container">
		<!-- 导航栏 -->
		<nav style="min-width: 767px" class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<p class="navbar-text text-center"
						style="color: #ffffff; font-size: 22px;">智云科技融合云系统</p>
				</div>
				<div id="navbar_right" class="nav navbar-nav navbar-right">
					<div style="float: left; color: white; padding: 13px 10px 13px 0" class="text-center" id="logOut">
						<span style="font-size: 23px"> <img
							src="<%=basePath%>img/bg-logout.png">
						</span> <font id="logOutFont">登出</font>
					</div>
					<div style="float: left; color: white; padding: 13px 10px 13px 10px" class="text-center" id="updatePwd">
						<span style="font-size: 23px"> <img
							src="<%=basePath%>img/bg-updatepwd.png">
						</span> 
						<font id="updatePwdFont">修改密码</font>
					</div>
					<div style="float: left; color: white; padding: 13px 10px 13px 10px" class="text-center" id="highSetting" onclick="highSetting('<%=settingUrl%>')">
						<span style="font-size: 23px"> <img
							src="<%=basePath%>img/bg-setting.png">
						</span> <font id="highSettingFont">配置</font>
					</div>
					<div style="float: left; padding: 13px 0px 13px 10px" class="text-center" id="moreService">
						<li class="dropdown">
							<a style="text-decoration: none;"
							class="dropdown-toggle" data-toggle="dropdown" href="#"> 
								<span
									style="font-size: 23px"> <img
										src="<%=basePath%>img/bg-more.png">
								</span> 
								<font id="moreServiceFont">更多服务</font>
							</a>
							<ul id="moreSev_list" class="dropdown-menu dropdown-menu-left">
								<li>
									<a class="text-center" style="padding: 10px 10px 1px 10px"> 
										<img style="width: 35px; height: 35px" alt="1" 
											src="../img/bg-problem.png" class="portrait">
										<h5>&nbsp;故障修复</h5>
									</a>
								</li>
								<li class="dropdown-divider text-center"></li>
								<li>
									<a class="text-center"style="padding: 10px 10px 1px 10px"> 
										<img style="width: 35px; height: 35px" alt="2"
											src="../img/bg-appdownload.png" class="portrait">
										<h5>&nbsp;应用下载</h5>
									</a>
								</li>
							</ul>
						</li>
					</div>
				</div>
			</div>
		</nav>
		<!-- 图片链接相关DIV -->
		<div class="row clearfix" style="background: #ffffff;margin-top:5px">
			<div class="col-xs-12">
				<div id="allZoom" class="row clearfix">
					<div class="col-sm-1 col-md-2"></div>
					<div class="col-xs-4 col-sm-2 col-md-2">
						<div id="computerZoom">
							<img id="computer" alt=""
								src="<%=basePath%>img/bg-computer.png" style="width:100%;height:auto"/>
						</div>
						<div style="text-align: center;margin-top:-10px">
							<h4>电脑分配</h4>
						</div>
					</div>
					<div class="col-sm-2 col-md-1"></div>
					<div class="col-xs-4 col-sm-2 col-md-2">
						<div id="applicationZoom">
							<img id="application" alt=""
								src="<%=basePath%>img/bg-application.png" style="width:100%;height:auto"/>
						</div>
						<div style="text-align: center;margin-top:-10px">
							<h4>应用部署</h4>
						</div>
					</div>
					<div class="col-sm-2 col-md-1"></div>
					<div class="col-xs-4 col-sm-2 col-md-2">
						<div id="storageZoom">
							<img id="storage" alt=""
								src="<%=basePath%>img/bg-storage.png" style="width:100%;height:auto"/>
						</div>
						<div style="text-align: center;margin-top:-10px">
							<h4>私有云盘</h4>
						</div>
					</div>
					<div class="col-sm-1 col-md-2"></div>
				</div>
			</div>
		</div>					
				
		<!-- 各种数据表格-->
		<div class="row clearfix" style="background: #ededed;margin-top:3px;height:400px">						
			<!-- Tab页 -->
			<ul class="tab nav nav-tabs" role="tablist">
				<li id="tab_computerInf" class="active" role="presentation" data-id="tab_computerInfo"><a href="#">电脑信息</a></li>
				<li id="tab_applicationInf" role="presentation" data-id="tab_application"><a href="#">应用信息</a></li>
				<li id="tab_storageInf"  role="presentation" data-id="tab_storage"><a href="#">云盘信息</a></li>
			</ul>
			<!-- 电脑情况数据表 -->
		    <div class="tab-content active" id="tab_computerInfo" style="margin-top:-10px">
				<div class="panel-body" id="cp_panelc">
					<%@include file="../jsp/computerTable.jsp"%>
					<table class="table table-striped table-bordered table-hover"
						id="tb_computerInfo"></table>
				</div>
			</div>
			<!-- 应用情况数据表 -->
			<div class="tab-content active" id="tab_application">
				<table class="table table-striped table-bordered table-hover" id="tb_application"></table>
			</div>
			<!-- 存储情况数据表 -->
			<div class="tab-content active" id="tab_storage">
				<div class="panel-body" id="cp_panels">
				    <%@include file="../jsp/storageTable.jsp"%>
					<%-- <%@include file="../jsp/storageTable.jsp"%> --%>
					<table class="table table-striped table-bordered table-hover"
						id="tb_storage"></table>
				</div>
			</div>
		</div>
		
		<!-- 饼形图设计 -->
		<div class="row clearfix" style="background: #ededed;margin-top:5px">
			<div class="col-xs-12 column">
				<div id="chart" class="row clearfix" style="margin-top:10px">	
					<div class="col-xs-0"></div>							
					<div class="col-xs-4">									
						<div id="highChartCpu" class="col-sm-10 col-md-8 col-lg-7 col-sm-offset-1 col-md-offset-2"></div>
					</div>
					<div class="col-xs-0"></div>
					<div class="col-xs-4">
						<div id="highChartMemory" class="col-sm-10 col-md-8 col-lg-7 col-sm-offset-1 col-md-offset-2"></div>							
					</div>	
					<div class="col-xs-0"></div>
					<div class="col-xs-4">
						<div id="highChartCapacity" class="col-sm-10 col-md-8 col-lg-7 col-sm-offset-1 col-md-offset-2"></div>							
					</div>	
					<div class="col-xs-0"></div>																						
				</div>
			</div>
		</div>					
		
		<div class="row clearfix" style="margin-top:0px">
			<div class="col-xs-12 column">
			<div class="col-xs-0 col-sm-3 col-md-4 column"></div>
			<div class="col-xs-12 col-sm-6 col-md-4 column text-center">
				<span style="font-size:15px">
		    		<img src="<%=basePath%>img/bg-copyright.png">
		    	</span>	
				<font style="font-size:12px">武汉智云方达信息科技有限公司  版权所有</font>
			</div>
			<div class="col-xs-0 col-sm-3 col-md-4 column"></div>							
			</div>
		</div>
	</div>	
	<%@include file="../include/creatVmModal.jsp"%>
	<%@include file="../include/creatStorageModal.jsp"%>
	<%@include file="../include/updatePassModal.jsp"%>
	<%@include file="../include/updateVmNameModal.jsp"%>
	<%@include file="../include/updateSgNumModal.jsp"%>	
	<script src="<%=basePath%>jquery/jquery-1.11.1.js"></script>
	<script src="<%=basePath%>jquery/jquery.resizeEnd.js"></script>
	<script src="<%=basePath%>jquery/jquery.zoomImgRollover.js"></script>
	<script src="<%=basePath%>jquery/highcharts.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootstrap-hover-dropdown.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootstrap-table.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="<%=basePath%>bootstrap/js/Chart-1.0.1-beta.4.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootbox.js"></script>
	<script src="<%=basePath%>js/vmController.js"></script>
	<script src="<%=basePath%>js/sgController.js"></script>
	<script src="<%=basePath%>js/commonController.js"></script>
	<script src="<%=basePath%>js/mainMenuController.js"></script>
	<script src="<%=basePath%>js/dropdownInitController.js"></script>
	<script src="<%=basePath%>js/tableInitController.js"></script>
	<script src="<%=basePath%>js/chartInitController.js"></script>
	<script src="<%=basePath%>js/cssController.js"></script>	
</body>
</html>