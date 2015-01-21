<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="yukjin" uri="www.yukjin.com" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store by yukjin</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="Css/jquery.autocomplete.css" />
<script src="Jscript/jquery-1.4.2.js"></script>
<script src="Jscript/register.js"></script>
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
        <li><a href="about.jsp">about us</a></li>
        <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("计算机","utf-8") %>">books</a></li>
        <li><a href="newbook.do?page=1">new books</a></li>
        <li><a href="Myaccount.jsp">my account</a></li>
        <li class="selected"><a href="register.jsp">register</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content" id="leftcontent">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Register</div>
      <div class="feat_prod_box_details">
        <p class="details">各位花费几秒即可注册，电话、职业以及地址将作为送货地点。 </p>
        <div class="contact_form">
          <div class="form_subtitle">create new account</div>
       <form name="register" action="register.do" method="post">
             <div class="form_row">
              <label class="contact"><strong>Username:</strong></label>
              <input type="text" class="contact_input" id="txtUserName" />
              <div id="userNameTip" style="display:none;" class="tip"></div>
            </div>
            <div class="form_row">
              <label class="contact"><strong>Password:</strong></label>
              <input type="text" class="contact_input" id="txtPswd"/>
              <span id="pswdTip" style="display:none;" class="tip"></span>
            </div>
            <div class="form_row">
              <label class="contact"><strong>Email:</strong></label>
              <input type="text" class="contact_input" id="txtEmail"/>
              <span id="emailTip" style="display:none;" class="tip"></span>
            </div>
            <div class="form_row">
              <label class="contact"><strong>Phone:</strong></label>
              <input type="text" class="contact_input" id="txtPhone" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Profession:</strong></label>
              <input type="text" class="contact_input" id="txtProfession" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Address:</strong></label>
              <input type="text" class="contact_input" id="txtAddr" />
            </div>
            
            <div class="form_row">
              <input type="button" class="register" value="register" />
            </div>
          </form>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
       <div class="form_row">
            <label class="contact"><strong>Search</strong></label>
            <form action="search.do" method="get">
            <input type="text" id="txtSearch" class="searchInput" name="bookname"/>
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
 <%@ include file="footer.html" %>
</div>
</body>
</html>
