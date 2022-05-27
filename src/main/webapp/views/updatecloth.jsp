<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Page!</title>
</head>
<body>
<c:if test="${update=='success'}">
<h3 style="color: green;">Successfully Updated!</h3>
</c:if>
<c:if test="${update=='failed'}">
<h3 style="color: red;">Something Went wrong!</h3>
</c:if>
<form action="update" method="post">
Select Id: <select name="cid">
<c:forEach items="${ids}" var="v">
<option>${v}</option>
</c:forEach>
</select><br>
<hr>
Cloth Name : <input type ="text" name="name" required/><br><br>
Cloth Price : <input type="number" name="price" required/><br><br>
Cloth Brand : <input type="text" name="brand" required/><br><br>
<button>Update</button>
</form>
<hr>
<hr>
</body>
</html>