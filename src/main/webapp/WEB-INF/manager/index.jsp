<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"	prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"	prefix="fmt" %>
<%@ include file="../GlobalHeader.jsp"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div>
			<div class="page-header">
			 <h1>内部信息系统&emsp;<small><a href="/logout">logout</a></small></h1>
			</div>
		</div>
		<div style="padding:10px;">
			<div class="tabbable" id="tabs">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-base" data-toggle="tab">系统基本参数</a>
					</li>
					<li>
						<a href="#panel-msg" data-toggle="tab">信息管理</a>
					</li>
				</ul>
				
				<div class="tab-content" style="padding:10px;">
					<!-- base tab -->
					<div class="tab-pane active" id="panel-base">
						
						<!-- cfg configuration -->
						<div class="col-xs-6">
							<div class="panel panel-default">
							  <div class="panel-heading">系统参数配置&emsp;<a href="/cfg/init">重新加载</a></div>
							  <div class="panel-body">
							    <form action="/cfg" method="post" class="form-horizontal" style="padding:10px;">
							    	<c:forEach items="${cfgs}" var="cfg">
							    	<div class="form-group">
								    	<div class="input-group">
								     	 <div class="input-group-addon">${cfg.name()}</div>
								     	 <input class="form-control" type="text" name="${cfg.name()}" value="${cfg.value}">
								    	</div>
								    </div>
							    	</c:forEach>
							    	
							    	<button type="submit" class="btn btn-primary">修改</button>
							    </form>
							  </div>
							</div>
						</div>
					</div>
					
					<!-- msg tab -->
					<div class="tab-pane" id="panel-msg">
						<!-- Message publish -->
						<div class="col-xs-6">
							<div class="panel panel-default">
							  <div class="panel-heading">发送站内消息</div>
							  <div class="panel-body">
							    <form action="/api/msg" method="post" class="form-horizontal" style="padding:10px;">
							    	<div class="form-group">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Title</div>
								     	 <input class="form-control" type="text" name="title" placeholder="Message Title">
								    	</div>
								    </div>
								    <div class="form-group">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Content</div>
								     	 <input class="form-control" type="text" name="content" placeholder="Message Content">
								    	</div>
								    </div>
								    
								    <div class="form-group" id="indateDiv" style="display:none">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Indate Days(天)</div>
								     	 <input class="form-control" type="number" name="indateDay" placeholder="Message Indate Days" value="-1">
								    	</div>
								    </div>
								    <div class="form-group" id="sendToDiv" style="display:none"> 
								    	<div class="input-group">
								     	 <div class="input-group-addon">Admin Send To</div>
								     	 <input class="form-control" type="text" name="recId" placeholder="Message Recevier ID">
								    	</div>
								    </div>
								    <div class="checkbox">
								     <label>
								       <input id="indateCheck" type="checkbox" name="timeLimit" >Time limitation Msg 
								     </label>
								     <label>
								       <input id="publicCheck" type="radio" name="type" value="PUBLIC" checked >Public
								     </label>
								     <label>
								       <input id="privateCheck" type="radio" name="type" value="PRIVATE">Private
								     </label>
								    </div>
								    <button type="submit" class="btn btn-primary">发送</button>
							    </form>
							  </div>
							</div>
						</div>
						
						<!-- Message Record -->
						<div class="col-xs-6">
						
							<div class="panel panel-default">
							  <div class="panel-heading">消息记录</div>
							  <div class="panel-body">
							  <table class="table table-hover">
							      <thead>
							        <tr>
							          <th>#</th>
							          <th>Title</th>
							          <th>Content</th>
							          <th>Indate</th>
							          <th>Type</th>
							        </tr>
							      </thead>
							      <tbody>
							        <c:forEach items="${msgs}" var="msg">
							        <tr>
							          <td>${msg.id}</td>
							          <td>${msg.title}</td>
							          <td>${msg.content}</td>
							          <td>
							          <c:if test="${empty msg.indate}">一直有效</c:if>
							          <c:if test="${!empty msg.indate}">
							          <fmt:formatDate value="${msg.indate}" pattern="yyyy-MM-dd mm:hh" />
							          </c:if>
							          </td>
							          <td>${msg.type.name()}</td>
							        </tr>
							        </c:forEach>
							      </tbody>
							    </table>
							  </div>
							</div>

						</div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$("#publicCheck").click(function(){
			$("#sendToDiv").hide();
		});
		$("#privateCheck").click(function(){
			$("#sendToDiv").show();
		});
		$("#indateCheck").click(function(){
			$("#indateDiv").toggle();
		});
	});
</script>
<%@ include file="../GlobalFooter.jsp"%>