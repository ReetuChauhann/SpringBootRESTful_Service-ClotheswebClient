<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3 style="color: blue;">View all Clothes here</h3> 

<c:forEach items="${allClothess}" var="x">
<div style="color: black; font: bold; border: thick;">
Cloth Id: <c:out value="${x.cid}"></c:out><br>
Cloth Name <c:out value="${x.name}"></c:out><br>
Cloth Price <c:out value="${x.price}"></c:out><br>
Cloth Brand <c:out value="${x.brand}"></c:out><br>

<img alt="" src="getimage?cid=${x.cid}" height="70px" width="70px"><br>

<form action="updateimage" method="post" enctype="multipart/form-data">
  				<input type="hidden" name="cid" value="${x.cid}" />
				Cloth Photo: <input type="file" name="image" required/><br/><br/>
				<input type="submit" value="Update Image"/>
			</form>
<hr><hr>
</c:forEach>
</div>



</body>
</html>