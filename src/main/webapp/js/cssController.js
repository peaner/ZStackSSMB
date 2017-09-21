/**
 * 
 */
$(function(){
	resizeBodyByNav();
	resizeDropdownMenuWidth();
	$(window).resize(function(){
		resizeBodyByNav();
		resizeImg();
		resizeDropdownMenuWidth();
		initZoom();	
		setChartHeight();
	});
	$(window).resizeEnd({
		delay : 0
	},function(){
		resizeBodyByNav();
		resizeImg();
		resizeDropdownMenuWidth();
		setChartHeight();
	});
	mouseInDropdown();
	resizeDropdownPosition();
});

/**
* 根据图片上一级div大小,当窗口发生改变时,动态调节图片大小
**/
function resizeImg(){
	var parentWidth=$("#computerZoom").parent().width();
	$("#allZoom").find("img").css({
		"width":parentWidth,"height":parentWidth
		});
}

/**
* 当窗口发生改变,动态调节下拉框的宽度
**/
function resizeDropdownMenuWidth(){
	$("ul.dropdown-menu-left").css({
		"margin-top":0,"padding":0,"border-width":0,"width":$("li.dropdown").css("width")
	});
}

/**
* 当窗口发生改变,动态调节下拉框的位置(紧贴导航栏底部)
**/
function resizeDropdownPosition(){
	$("li.dropdown").css("padding-bottom",$(".navbar-right").children("div").css("padding-top"));
	$("#navbar_right").children("div").css("padding-bottom",0);
}

/**
* 当窗口发生改变,动态调节body的上内边距,使内容不被导航栏遮住
**/
function resizeBodyByNav(){
	$("body").css("padding-top",$("nav").css("height"));
}

/**
* 设置"更多服务"按钮的移入移出触发事件
**/
function mouseInDropdown(){
	$("#moreService>li").bind("mouseover",function(){
		$("#moreSev_list").stop(true, false).slideDown();
		$(this).addClass("open");
		$(this).find("a.dropdown-toggle").attr("aria-expanded","true");
	});
	
	$("#moreService li").bind("mouseout",function(){
		$("#moreSev_list").stop(true, false).slideUp();
		$(this).removeClass("open");
		$(this).find("a.dropdown-toggle").attr("aria-expanded","false");
	});
}
