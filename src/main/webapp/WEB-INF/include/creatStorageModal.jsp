<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.warning{
	    border: 1px red solid;
	    color: red;
	}
	.success{
		border:1px #37CC70 solid;
		color:black;
	}
</style>
</head>
<body>
	<!-- 存储模态框示例（Modal） -->
	<form method="post" action="" class="form-horizontal" role="form"
		id="formS_data" onsubmit="return submit_form()"
		style="margin: 20px;">
		<div class="modal fade" id="creatStorageModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" >存储分配信息</h4>
					</div>
					
					<div class="modal-body">
						<form class="form-horizontal" role="form">												
							<div class="form-group">
								<label for="sgLogin" class="col-sm-3 control-label">账号：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="sgLogin"
										value="" id="sgLogin" placeholder="账号">
								</div>
								<div style="margin-top:5px" id="sgLoginAlert" name="sgLoginAlert"></div>
							</div>
							<div class="form-group">
								<label for="sgName" class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="sgName"
										value="" id="sgName" placeholder="用户名">
								</div>
								<div style="margin-top:5px" id="sgNameAlert" name="sgNameAlert"></div>
							</div>
							<div class="form-group">
								<label for="sgNum" class="col-sm-3 control-label">分配存储：</label>
								<div class="col-sm-5">
									<div class="input-group">
										<input type="text" class="form-control" name="sgNum" value=""
											id="sgNum" placeholder="分配存储大小">
										<div class="input-group-addon"
											style="background-color: #D3F8F8">G</div>
									</div>
								</div>
								<div style="margin-top:5px" id="sgNumAlert" name="sgNumAlert"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer" style="background:#F0F7FC">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="creatSgSumbit" class="btn btn-primary">提交</button>
						<span id="tip"> </span>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
</body>
</html>