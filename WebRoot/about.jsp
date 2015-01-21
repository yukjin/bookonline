<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="yukjin" uri="www.yukjin.com" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store by yukjin</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="Css/jquery.autocomplete.css" />
<script type="text/javascript" src="Jscript/music.js"></script>
<script type="text/javascript" src="Jscript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="Jscript/jquery.autocomplete.js"></script>
<script type="text/javascript" src="Jscript/autosearch.js"></script>
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="hotbook.do"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    <div id="menu">
      <ul>
        <li><a href="hotbook.do">home</a></li>
        <li class="selected"><a href="about.jsp">about us</a></li>
        <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("计算机","utf-8") %>">books</a></li>
        <li><a href="newbook.do?page=1">new books</a></li>
        <li><a href="Myaccount.jsp">my account</a></li>
        <li><a href="register.jsp">register</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>About us</div>
      <div class="feat_prod_box_details">
        <p class="details"> <img src="images/about.gif" alt="" title="" class="right" /> 这是一个图书在线交易平台，我们拥有海量书籍并且种类繁多，相信总有一本适合您.<br />
          <br />
          本平台旨在能让到访者更方便的寻找到属于自己的书籍,我们郑重承诺您所买到的每一本书必然是正版的 <br />
          <br />
           目前我们的平台尚在完善当中，如果您有任何意见或建议我们洗耳恭听！ </p>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
       <div class="form_row">
            <label class="contact"><strong>Search</strong></label>
            <form action="search.do" method="get">
            <input type="text" class="contact_input" id="txtSearch" name="bookname"/>
            </form>
          </div>
          <yukjin:user user="${sessionScope.user}"/>
      <yukjin:cart cart="${sessionScope.cart}"/>
      <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
      <%@include file="aboutUs.jsp" %>
      <div class="right_box">
        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div>
       <%@include file="categoryList.jsp" %>
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
