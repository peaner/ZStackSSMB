$(function() {
	updateChartValue();
	setChartHeight();
	$(".custom-close").on('click', function() {
		$('#myModal').modal('hide');
	});
});

/**
 * 窗口大小不同,饼形图自动调整大小
 */
function setChartHeight(){
	var $chart=$("#chart");
	var chartHeight=$("#highChartCpu").css("width");
	chartHeight=parseInt(chartHeight)*1.2+"";
	$chart.find("svg").css("height",chartHeight);
	$chart.children("div:odd").children("div").css("height",chartHeight);
}

/**
 * 更新获取饼图数据
 */
function updateChartValue(){
	getCapacityChartValue();
	getCpuAndMemoryChartValue();
}

/**
 * 初始化磁盘饼图的数据
 */
function getCapacityChartValue() {
	$.ajax({
		type : "post",
		url : "../init/capacityChartInit.do",
		async : true,
		beforeSend : function() {
			// alert("请求前的处理");
		},
		success : function(result) {
			var availableCapacity = 0;
			var totalCapacity = 0;
			var data = null;
			if (result != null) {
				// 得到实体类，实体类已定通过Json 格式转一下 ，JS中通过eval
				data = eval(result);
			}
			if (data != null && !isEmpty(data[0].availableCapacity)
					&& !isEmpty(data[0].totalCapacity)) {
				availableCapacity = Number(data[0].availableCapacity);
				totalCapacity = Number(data[0].totalCapacity);
			}
			if(totalCapacity != 0){
				getHighChart('highChartCapacity', '磁盘利用率', availableCapacity, totalCapacity);
			}			
		},
		error : function() {
			bootbox.alert("获取磁盘利用率出现错误！");
		}
	});
}

function getCpuAndMemoryChartValue() {
	$.ajax({
		type : "post",
		url : "../init/cpuAndMemoryChartInit.do",
		async : true,
		beforeSend : function() {
			// alert("请求前的处理");
		},
		success : function(result) {
			var availableCpu = 0;
			var availableMemory = 0;
			var totalCpu = 0;
			var totalMemory = 0;
			var data = null;
			if (result != null) {
				// 得到实体类，实体类已定通过Json 格式转一下 ，JS中通过eval
				data = eval(result);
			}
			if (data != null) {
				if (!isEmpty(data.availableCpu) && !isEmpty(data.totalCpu)) {
					availableCpu = Number(data.availableCpu);
					totalCpu = Number(data.totalCpu);
				}
				if (!isEmpty(data.availableCpu) && !isEmpty(data.availableMemory)) {
					availableMemory = Number(data.availableMemory);
					totalMemory = Number(data.totalMemory);
				}
			}	
			if(totalMemory != 0){
				getHighChart('highChartCpu', 'CPU利用率', availableCpu, totalCpu);
				getHighChart('highChartMemory', '内存利用率', availableMemory, totalMemory);
			}
		},
		error : function() {
			bootbox.alert("获取CPU或者内存利用率出现错误！");
		}
	});
}

/**
 *  饼形图相关设置
 * @param chartName 饼形图的ID名称
 * @param titleText	标题
 * @param available	可用
 * @param total 总量(与可用单位一致)
 */
function getHighChart(chartName, titleText, available,  total) {
	//初始化图形为使用网络100%，异常情况也显示100%
	var used = 100;
	var unUsed = 0;
	//比例显示计算处理
	if(!isEmpty(available) && !isEmpty(total) && total != 0){
		unUsed = Number(((available/total)*100).toFixed(2));
		used = Number(100 - unUsed);
		total = total.toFixed(2);
	}	
	var seriesName = '总量('+ total +'G)';
	//CPU利用率单位不一样
	if(chartName == "highChartCpu"){
		seriesName = '总量('+ total/10 +'核)';;
	}
	//饼形图相关设置
	Highcharts.chart(chartName, {
		chart : {
			backgroundColor: 'rgba(255, 255, 255, 0)',
            plotBorderColor : null,
            plotBackgroundColor: null,
            plotBackgroundImage:null,
            plotBorderWidth: null,
            plotShadow: false,
            zoomType: 'x',
			type : 'pie'
		},
		title : {
			text : titleText,
			y : 5,
			align : "left",
			verticalAlign : "top",
			style:{
				color: '#000000',
				fontSize: '14px'
			}
		},
		tooltip : {			
			pointFormat : '{point.percentage:.1f}%: {series.name}',			//提示框文字样式
			backgroundColor: '#ffffff' 										//提示框背景颜色
		},
		credits : {
			enabled: false	//删除右下角的highcharts.com链接
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : false
				},
				showInLegend : true
			}
		},
		//图例
		legend : {
		    itemStyle : {
		        'fontSize' : '10px'
		    },
		    itemMarginTop:-10,
		    itemDistance:10
		},
		series : [  {
			type : 'pie',
			name : seriesName,
			data : [ {
				name : '已用',
				y : used,
				sliced : true,
				color : "#ff0000",
				selected : false
			},{
				name : '可用',
				y : unUsed,
				sliced : false,
				color : "#37cd00",
				selected : false
			}]
		} ],
		responsive: {
			rules: [{              	 // 在图表小于 500px 的情况下关闭图例
				condition: {         // 响应条件
			    maxWidth: 150
			    },
			    chartOptions: {      // 响应内容
			    	title : {
						text : titleText,
						y : 1,
						align : "left",
						verticalAlign : "top",
						style:{
							color: '#000000',
							fontSize:'10px'
						}
					},
			    	legend: {
			    		itemStyle : {
			    			itemMarginTop:0,
			    		},
			    		layout: 'horizontal',
			    		itemMarginTop:1,
					},
				}
			}]
		}
	});
}


