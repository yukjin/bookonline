<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>autocomplete插件</title>
    <script type="text/javascript" 
            src="Jscript/jquery-1.4.2.js">
    </script>
    <script type="text/javascript" 
            src="Jscript/jquery.autocomplete.js">
    </script>
    <link rel="stylesheet" type="text/css" 
            href="Css/jquery.autocomplete.css" />
    <script type="text/javascript">
        $(function() {
            $("#txtSearch").autocomplete("demo.do?p=1", {
                minChars: 0}) //双击空白文本框时显示全部提示数据
                
        })
    </script>
</head>
<body>
<input type="text" id="txtSearch"/>
</body>
</html>
