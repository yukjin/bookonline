<%@ page language="java" import="java.util.*,bookOnline.BookInfo" pageEncoding="utf-8"%>
<%@ taglib prefix="yukjin" uri="www.yukjin.com"%>
<jsp:useBean id="bookinfo" class="bookOnline.BookInfo" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store by yukjin</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" href="lightbox.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="Css/jquery.autocomplete.css" />
<script src="js/prototype.js" type="text/javascript"></script>
<script src="js/scriptaculous.js?load=effects" type="text/javascript"></script>
<script src="js/lightbox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/java.js"></script>
<script type="text/javascript" src="Jscript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="Jscript/jquery.autocomplete.js"></script>
<script type="text/javascript" src="Jscript/autosearch.js"></script>
<script type="text/javascript" src="Jscript/score.js"></script>

</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="hotbook.do"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    <div id="menu">
      <ul>
        <li class="selected"><a href="hotbook.do">home</a></li>
        <li><a href="about.jsp">about us</a></li>
        <li><a href="category.do?page=1&booktype=<%=URLEncoder.encode("计算机","utf-8") %>">books</a></li>
        <li><a href="newBook.jsp">new books</a></li>
        <li><a href="Myaccount.jsp">my account</a></li>
        <li><a href="register.jsp">register</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="crumb_nav"> <a href="hotbook.do">home</a> &gt;&gt; <jsp:getProperty property="bookname" name="bookinfo"/> </div>
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><jsp:getProperty property="bookname" name="bookinfo"/></div>
      <div class="feat_prod_box_details">
        <div class="prod_img"><a href="details.do?bookname=<jsp:getProperty property='bookname' name='bookinfo'/>"><img src="images/pre<jsp:getProperty property='bookpic' name='bookinfo'/>" alt="" title="" border="0" /></a> <br />
          <br />
          <a href="images/<jsp:getProperty property='bookpic' name='bookinfo'/>" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0" /></a> </div>
        <div class="prod_det_box">
          <div class="box_top"></div>
          <div class="box_center">
          <div class="prod_title">作者</div>
            <p class="details"><jsp:getProperty property="bookauthor" name="bookinfo"/></p>
            <div class="prod_title">出版社</div>
            <p class="details"><jsp:getProperty property="bookconcern" name="bookinfo"/></p>
            <div class="prod_title">出版日期</div>
            <p class="details"><jsp:getProperty property="publishtime" name="bookinfo"/></p>
            <div class="prod_title">ISBN</div>
            <p class="details" id="isbn"><jsp:getProperty property="bookid" name="bookinfo"/></p>
            <div class="prod_title">库存</div>
            <p class="details"><jsp:getProperty property="booknum" name="bookinfo"/></p>
            <div class="price"><strong>PRICE:</strong> <span class="red"><jsp:getProperty name="bookinfo" property="bookprice"/>元</span></div>
            <div class="price"><strong>RANK:</strong> 
            <img src="images/pre.png" style="height:18px; width:18px;" class="rank" value="1">
    		<img src="images/pre.png" style="height:18px; width:18px;" class="rank" value="2">
    		<img src="images/pre.png" style="height:18px; width:18px;" class="rank" value="3">
   		    <img src="images/pre.png" style="height:18px; width:18px;" class="rank" value="4">
    		<img src="images/pre.png" style="height:18px; width:18px;" class="rank" value="5"> 
    		</div>
            <a href="details.do?add=1&bookname=${bookinfo.bookname}" class="more"><img src="images/addcart.jpg" alt="" title="" border="0" /></a>
            <yukjin:addBook bookinfo="${requestScope.bookinfo}" add="${param.add}" cart="${sessionScope.cart}"/>
            <div class="clear"></div>
          </div>
          <div class="box_bottom"></div>
        </div>
        <div class="clear"></div>
      </div>
      <div id="demo" class="demolayout">
        <ul id="demo-nav" class="demolayout">
          <li><a class="active" href="#">More details</a></li>
          <li><a class="" href="#">Related books</a></li>
        </ul>
        <div class="tabs-container">
          <div style="display: block;" class="tab" id="tab1">
            <p class="more_details">
            
            <div class="prod_title">内容简介</div>
            <p class="details"><jsp:getProperty property="bookintro" name="bookinfo"/></p>
            <div class="prod_title">作者简介</div>
            <p class="details"><jsp:getProperty property="authorintro" name="bookinfo"/></p></p>
           
            <!--  <p class="more_details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. </p>-->
          </div>
          <div style="display: none;" class="tab" id="tab2">
            <yukjin:showBookList showType="category" bookList="${requestScope.bookList}"/>
            <div class="clear"></div>
          </div>
        </div>
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
  <%@ include file="footer.html" %>
</div>
</body>
<script type="text/javascript">

var tabber1 = new Yetii({
id: 'demo'
});

</script>
</html>
