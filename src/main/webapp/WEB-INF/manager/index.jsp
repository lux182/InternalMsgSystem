<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../GlobalHeader.jsp"%>
<div class="page-header">
  <h1>内部信息系统<small>www.dianrong.com</small></h1>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-base" data-toggle="tab">系统基本参数</a>
					</li>
					<li>
						<a href="#panel-msg" data-toggle="tab">信息管理</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-base">
						<p>
							第一部分内容.
						</p>
					</div>
					<div class="tab-pane" id="panel-msg">
						<p>
							第二部分内容.
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../GlobalFooter.jsp"%>