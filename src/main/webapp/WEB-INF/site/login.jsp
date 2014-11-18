<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../GlobalHeader.jsp"%>
<style>
body {
	background: #F9F9F9;
}

.login-form {
	padding: 20px;
	border: 5px solid #dedede;
	box-shadow: 2px 2px 10px black;
	margin-top: 200px;
}
</style>
<div class="container">
	<div class="col-md-6 col-md-offset-3 login-form">
		<form class="form-horizontal" role="form" action="/login" method="post">
			<fieldset>
				<legend><img src="/static/img/admin_login_log.jpg" alt="" width="50">管理员登陆</legend>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">用户名:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username" placeholder="管理员账号">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密&emsp;码:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password"
							placeholder="管理员密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Sign in</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<%@ include file="../GlobalFooter.jsp"%>