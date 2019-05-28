<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String loginEmployeeName=(String)session.getAttribute("loginemployeeName"); %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>酒店后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <ul class="navbar-list clearfix">
                <li><a class="on" >环境变量酒店</a></li>            
            </ul>          
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">        
                <li><%= loginEmployeeName %>,欢迎你&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li><a href="Employee_logout.action">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <ul class="sub-menu">
                        <li><a href="Employee_queryHomeStatus.action"><i class="icon-font"></i>查询所有房间状态</a></li>
                        <li><a href="Employee_dealOrder.action"><i class="icon-font"></i>处理预定订单</a></li>
                        <li><a href="Employee_dealLivein.action"><i class="icon-font"></i>处理入住</a></li>
                        <li><a href="Employee_NowStatus.action"><i class="icon-font"></i>查看目前住房状态</a></li>
                         <li><a href="Employee_historyStatus.action"><i class="icon-font"></i>查看历史入住信息</a></li>     
                        <li><a href="Employee_updateRoom.action"><i class="icon-font"></i>修改房间信息</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
        <div class="main-wrap">
          <div class="crumb-wrap">
            <div class="crumb-list">
            <i class="icon-font"></i>
            <a href="Employee_returnToIndex.action">首页</a>
            <span class="crumb-step">&gt;</span>
            <span class="crumb-name">查询所有房间状态</span>
            </div>
           </div>
           <div class="search-wrap">
            <div class="search-content">
                <form action="Employee_queryOneTypeHome.action" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择房间类型:</th>
                            <td>
                                <select name="roomtype" id="1">
                                    <option value="*" >全部</option>
                                    <option value="单人房">单人房</option>
                                    <option value="双人房">双人房</option>
                                    <option value="大床房">大床房</option>
                                    <option value="电脑房">电脑房</option>
                                    <option value="经济房">经济房</option>
                                </select>
                            </td>
                            <th width="120">选择入住情况:</th>
                            <td>
                                <select name="status" id="2">
                                    <option value="*" >全部</option>
                                    <option value="空房">空房</option>
                                    <option value="非空">非空</option>          
                                </select>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form action="#" method="post" id="myform" name="myform">
                <div class="config-items">                   
                    <div class="result-content">
                        <table width="100%" class="insert-tab">
                         <tr >
									<td>房间号</td>
									<td>房间种类</td>
									<td>价格(元/晚)</td>
									<td>目前入住状态</td>								
						 </tr>
						 <!-- 遍历开始 -->
						 <s:iterator value="#request.AllHome_list" var="AllHome">
								<tr class="list">
									<td><s:property value="#AllHome.roomnumber" /></td>
									<td><s:property value="#AllHome.roomtype" /></td>
                                    <td><s:property value="#AllHome.price" /></td>
									<td><s:property value="#AllHome.status" /></td>
								</tr>
						 </s:iterator>
						 <!-- 遍历结束 -->
                         </table>
                    </div>
                </div>              
            </form>
        </div>
    </div>
</div>
</body>
</html>