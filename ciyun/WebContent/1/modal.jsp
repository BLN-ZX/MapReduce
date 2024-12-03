<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>功能页面模版</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<%@include file="../basic/cssjs.jsp" %>
</head>

<body>
	<!-- 头部 -->
	<%@include file="../basic/top.jsp" %>

	<!-- content start -->
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3">
				<!-- 左侧导航 -->
				<%@include file="../basic/left.jsp" %>
			</div>
			
			<!-- right content start -->
			<div class="span9">
				<div class="session">
					
					<div>
						<form class="form-inline">
							<fieldset>
								
							</fieldset>
						</form>
					</div>
					
				</div>
			</div>
			<!-- right content end -->
		</div>
	</div>
	<!-- content end -->
</body>
</html>
