<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="utf-8"%>
<%@ taglib prefix="yukjin" uri="www.yukjin.com" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store by yukjin</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="Css/jquery.autocomplete.css" />

<link href="skin/pink.flag/jplayer.pink.flag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
<script type="text/javascript" src="Jscript/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="js/jplayer.playlist.min.js"></script>
<script type="text/javascript" src="Jscript/music.js"></script>
<script type="text/javascript" src="Jscript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="Jscript/jquery.autocomplete.js"></script>
<script type="text/javascript" src="Jscript/autosearch.js"></script>
<script type="text/javascript" src="Jscript/dragging.js"></script>
<script type="text/javascript" src="Jscript/ajax.js"></script>
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="hotbook.do"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    <div id="menu">
      <ul>
        <li class="selected"><a href="hotbook.do">home</a></li>
        <li><a href="about.jsp" target="_blank">about us</a></li>
        <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("计算机","utf-8") %>" target="_blank">books</a></li>
        <li><a href="newbook.do?page=1" target="_blank">new books</a></li>
        <li><a href="Myaccount.jsp" target="_blank">my account</a></li>
        <li><a href="register.jsp" target="_blank">register</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Featured books</div>
     <yukjin:showBookList showType="hot" bookList="${requestScope.bookList}"/>
      <div class="pagination"> 
      <yukjin:pagination type="hot" currPage="${requestScope.currPage}" pageNum="${requestScope.pageNum}" category="${requestScope.booktype}"/> 
      </div>
     <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
       <div class="form_row">
            <label class="contact"><strong>Search</strong></label>
            <form action="search.do" method="get" target="_blank">
            <input type="text" class="contact_input" id="txtSearch" name="bookname"/>
            </form>
          </div>
       <yukjin:user user="${sessionScope.user}"/>
      <yukjin:cart cart="${sessionScope.cart}"/>
      <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
       <%@include file="aboutUs.jsp" %>
      <div class="right_box">
        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div>
        <ul class="list">
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("计算机","utf-8") %>" target="_blank">计算机</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("英语","utf-8") %>" target="_blank">英语</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("经济","utf-8") %>" target="_blank">经济</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("法律","utf-8") %>" target="_blank">法律</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("小说","utf-8") %>" target="_blank">小说</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("心理","utf-8") %>" target="_blank">心理</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("生活","utf-8") %>" target="_blank">生活</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("名著","utf-8") %>" target="_blank">名著</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("哲学","utf-8") %>" target="_blank">哲学</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("职场","utf-8") %>" target="_blank">职场</a></li>
          <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("其他","utf-8") %>" target="_blank">其他</a></li>
        </ul>
        
        <div id="target" style="position:absolute; z-index:501; left: 100px; top: 188.5px" onmousedown="drag.down(event)" onmouseup="drag.up()" onmousemove="drag.move(event)">
        </div>
         <div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Entertainment</div>
        <ul class="list">
          <li><a href="#" id="l_music">Listen to Music</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
          <li><a href="#">有待开发</a></li>
        </ul>
      </div>
      
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  <!--end of center content-->
  
  <%@include file="footer.html" %>
</div>
</body>
</html>
