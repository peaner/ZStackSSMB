/**
 * 记录tab标签点击状态，1表示被点击，0表示未被点击
 */
var flag_computerInf = 1;
var flag_applicationInf = 0;
var flag_storageInf = 0;

$(function() {
	getComputerInfo();
});

/**
 * tab鼠标移动背景变色
 */
$("#tab_computerInf").mouseover(function() {
	$(this).addClass('active').siblings().removeClass('active');
}).mouseout(function() {
	if(flag_computerInf == 0){
		$(this).removeClass('active');
	}	
	if(flag_applicationInf == 1){
		$("#tab_applicationInf").addClass('active');
	}
	if(flag_storageInf == 1){
		$("#tab_storageInf").addClass('active');
	}
});
$("#tab_applicationInf").mouseover(function() {
	$(this).addClass('active').siblings().removeClass('active');
}).mouseout(function() {
	if(flag_applicationInf == 0){
		$(this).removeClass('active');
	}
	if(flag_computerInf == 1){
		$("#tab_computerInf").addClass('active');
	}
	if(flag_storageInf == 1){
		$("#tab_storageInf").addClass('active');
	}
});
$("#tab_storageInf").mouseover(function() {
	$(this).addClass('active').siblings().removeClass('active');
}).mouseout(function() {
	if(flag_storageInf == 0){
		$(this).removeClass('active');
	}
	if(flag_applicationInf == 1){
		$("#tab_applicationInf").addClass('active');
	}
	if(flag_computerInf == 1){
		$("#tab_computerInf").addClass('active');
	}
});

/**
 * tab选择页的功能实现
 */
$('.nav-tabs li').click(
		function() {
			$(this).addClass('active').siblings().removeClass('active');
			var _id = $(this).attr('data-id');
			$('.tabs-contents').find('#' + _id).addClass('active').siblings()
			.removeClass('active');
			$("#tab_computerInfo").hide();
			$("#tab_application").hide();
			$("#tab_storage").hide();
			switch (_id) {
			case "tab_computerInfo":
				refreshComputerInfo();
				getComputerInfo();
				$("#tab_computerInfo").show();
				flag_storageInf = 0;
				flag_applicationInf = 0;
				flag_computerInf = 1;
				break;
			case "tab_application":
				getApplicationInfo();
				$("#tab_application").show();
				flag_storageInf = 0;
				flag_applicationInf = 1;
				flag_computerInf = 0;
				break;
			case "tab_storage":
				refreshStorageInfo();
				getStorageInfo();
				$("#tab_storage").show();
				flag_storageInf = 1;
				flag_applicationInf = 0;
				flag_computerInf = 0;
				break;
			default:
				refreshComputerInfo();
			    //$("#tab_computerInfo").show();
			break;
			}
		});

/**
 * 电脑情况表初始化以及数据获取
 */
function getComputerInfo() {
	$("#toolbarStorage").hide();
	$('#tb_computerInfo').bootstrapTable({
		url : '../init/computerInfoInit.do', // 请求后台的URL（*）			
		method : 'get', // 请求方式（*）
		toolbar : '#toolbarComputer', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式			
		queryParams : function getParams(params) {
			var queryParams = params;
			queryParams.pageNumber = this.pageNumber;			
			return queryParams;
		}, 
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pagination : true, // 是否显示分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 5, 10, 20], // 可供选择的每页的行数（*）
		search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
		strictSearch : true,
		showColumns : false, // 是否显示所有的列
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		height : 350, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "index", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		columns : [ {
			checkbox : true
		},{
			field : 'index',
			title : '序号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'name',
			title : '电脑用户名',
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value, row, index) {
				return  '<a href="#" onclick="updateVmName('+index+');" title="修改用户名">'+value+'</a>';
			}
		}, {
			field : 'imageName',
			title : '操作系统',
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value, row, index) {
				return row.platform + "("+ value +")";
			}
		}, {
			field : 'ip',
			title : '访问IP',
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			field : 'state',
			title : '可用状态',
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value, row, index) {
				var label = 'label-default';			
				if (value == 'Running') {
					label = 'label-success';
				} else if (value == 'Stopped') {
					label = 'label-danger';
				} else if (value == 'Unknown') {
					label = 'label-warning';
				} else {
					label = 'label-default';
				}
				return "<label class='label-state " + label + "'>" + value+ "</label>";
			}
		}, {
			field : 'createDate',
			title : '创建日期',
			align : 'center',
			valign : 'middle',
			sortable : true
		} ],
		silent : true, // 刷新事件必须设置
		onClickRow : function(row, tr, field) {
			//进行你的操作，如弹出新窗口
		},
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},
		formatNoMatches : function() { // 没有匹配的结果
			return '无符合条件的记录';
		},
		formatRecordsPerPage: function (pageNumber) {
			return '每页显示 ' + pageNumber + ' 条记录';
		},
		formatShowingRows: function (pageFrom, pageTo, totalRows) {
			//return '显示第 ' + pageFrom + ' 到第 ' + pageTo + ' 条记录，总共 ' + totalRows + ' 条记录';
			return '总共 ' + totalRows + ' 条记录';
		},
		formatSearch: function () {
			return '搜索条件';
		},
		formatPaginationSwitch: function () {
			return '隐藏/显示分页';
		},
		formatRefresh: function () {
			return '刷新';
		},
		formatColumns: function () {
			return '列';
		},
		onLoadError : function(data) {
			$('#tb_computerInfo').bootstrapTable('removeAll');
		},
		onRefresh:function(params) {
			updateChartValue();
		},
		onLoadSuccess:function(data) {
			console.log(data);
		}
	});

	//BootstrapTable实现隐藏某列方法（showColumn显示；hideColumn隐藏）
	$('#tb_computerInfo').bootstrapTable('hideColumn', 'index');
}

/**
 * 电脑情况表更新
 */
function refreshComputerInfo() {
	$("#tb_computerInfo").bootstrapTable('refresh');
}

/**
 * 应用部署情况表初始化以及数据获取（暂时未实现）
 */
function getApplicationInfo() {
	$('#tb_application').bootstrapTable({
		//url : '../init/computerInfoInit.do', // 请求后台的URL（*）			
		method : 'get', // 请求方式（*）
		toolbar : '#1', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式			
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）			
		pagination : true, // 是否显示分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 1, // 每页的记录行数（*）
		pageList : [ 5, 10, 20 ], // 可供选择的每页的行数（*）
		search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
		strictSearch : true,
		showColumns : false, // 是否显示所有的列
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		height : 350, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "uuid", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		columns : [ {
			checkbox : true
		}, {
			field : 'ip',
			title : '访问IP',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'name',
			title : '应用名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'platform',
			title : '系统类型',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'state',
			title : '服务状态',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'uuid',
			title : '暂时显示',
			align : 'center',
			valign : 'middle'
		} ]
	});
}

/**
 * 存储情况表初始化以及数据获取
 */
function getStorageInfo() {
	$("#toolbarStorage").show();
	$('#tb_storage').bootstrapTable({
		url : '../storage/sgInfoInit.do', // 请求后台的URL（*）			
		method : 'get', // 请求方式（*）
		toolbar : '#toolbarStorage', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式			
		queryParams : function getParams(params) {
			var queryParams = params;
			queryParams.pageNumber = this.pageNumber;			
			return queryParams;
		}, 
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pagination : true, // 是否显示分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 5, 10, 20], // 可供选择的每页的行数（*）
		search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
		strictSearch : true,
		showColumns : false, // 是否显示所有的列
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		height : 350, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "userID", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		columns : [ {
			checkbox : true
		},
		{
			field : 'userID',
			title : '用户标识',
			align : 'center',
			valign : 'middle'
		},{
			field : 'userLoginID',
			title : '登录账号',
			align : 'center',
			sortable : true,
			valign : 'middle'
		}, {
			field : 'userName',
			title : '用户名',
			align : 'center',
			sortable : true,
			valign : 'middle'
		}, {
			field : 'totalSize',
			title : '总分配量',
			align : 'center', 
			sortable : true,
			valign : 'middle'
		}, {
			field : 'usedSize',
			title : '已使用',
			align : 'center',
			sortable : true,
			valign : 'middle'
		}, {
			field : 'updateDate',
			title : '日期',
			align : 'center',
			sortable : true,
			valign : 'middle'
		}, {
			field : 'operator',
			title : '操作者',
			align : 'center',
			sortable : true,
			valign : 'middle'
		} ],
		silent : true, // 刷新事件必须设置
		onClickRow : function(row, tr, field) {
			//进行你的操作，如弹出新窗口
		},
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},
		formatNoMatches : function() { // 没有匹配的结果
			return '无符合条件的记录';
		},
		formatRecordsPerPage: function (pageNumber) {
			return '每页显示 ' + pageNumber + ' 条记录';
		},
		formatShowingRows: function (pageFrom, pageTo, totalRows) {
			//return '显示第 ' + pageFrom + ' 到第 ' + pageTo + ' 条记录，总共 ' + totalRows + ' 条记录';
			return '总共 ' + totalRows + ' 条记录';
		},
		formatSearch: function () {
			return '搜索条件';
		},
		formatPaginationSwitch: function () {
			return '隐藏/显示分页';
		},
		formatRefresh: function () {
			return '刷新';
		},
		formatColumns: function () {
			return '列';
		},
		onLoadError : function(data) {
			$('#tb_storage').bootstrapTable('removeAll');
		},
		onRefresh:function(params) {
			
		},
		onLoadSuccess:function(data) {
			console.log(data);
		}

	});
	//BootstrapTable实现隐藏某列方法（showColumn显示；hideColumn隐藏）
	$('#tb_storage').bootstrapTable('hideColumn', 'userID');
}

/**
 * 存储情况表更新
 */
function refreshStorageInfo() {
	$("#tb_storage").bootstrapTable('refresh');
}

