<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h2>Hello World!</h2>

<p>处理器方法返回String表示视图名称</p>
<form action="returnString-view.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交参数">
</form>
<br/>
<p>处理器方法返回String表示视图完整路径</p>
<form action="returnString-view2.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交参数">
</form>
<br/>
<br/>


<%--http://localhost:8080/myWeb/开启服务器浏览器自动条状URL 且自动访问该页面index.jsp
/myWeb/some.do地址路径必须是前台路径，若为/some.do后台路径不能够访问--%>
<a href="/myWeb/some.do">发起some.do的请求</a><%--这里后台台路径/some.do不可以,前台路径/myWeb/some.do才可以 --%>
<%--

<p>提交参数给Controller</p>

<form action="/myWeb/some01.do" method="get">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交参数">
</form>

<br/>
<p>请求参数名和处理器方法的形参名不一样</p>
&lt;%&ndash;name：å¢å±å è¯¾  POST请求方式乱码问题 使用过滤器来解决&ndash;%&gt;
<form action="/myWeb/some01.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交参数">
</form>
<br/>
--%>

</body>
</html>
