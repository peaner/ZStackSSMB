<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="padding-top: 30%">
	<!-- 修改电脑用户名模型 -->
	<div class="modal" id="updateVmNameModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>修改电脑用户名</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">当前用户名:</label>
							<div class="col-sm-6">
								<input type="text" readonly="true" class="form-control"
									id="currentVmName">
							</div>
						</div>
						<div class="form-group">
							<label for="inputVmName" class="col-sm-3 control-label">修改用户名:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="newVmName"
									placeholder="请输入电脑用户名">
							</div>
							<div style="margin-top: 5px" id="checkVmNameAlert" name="checkVmNameAlert"></div>
						</div>
					</form>
				</div>
				<div style="background: #F0F7FC" class="modal-footer">					
					<button type="button" class="btn btn-primary"
						style="margin-left: 20px" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="updateVmName">修改</button>
				</div>
			</div>
			<input id="vmNameIndex" name="vmNameIndex" hidden ="hidden"/>
		</div>
	</div>

</body>
</html>