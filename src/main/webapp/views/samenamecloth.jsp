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
<form action="view" mthod="post">
Cloth Name: <input type="text" name="name" required/>
<button>View</button>
</form>
<hr><hr>
<c:forEach items="${Clothes}" var="c">
Cloth Id : <c:out value="${c.cid}"></c:out><br>
Cloth Name : <c:out value="${c.name}"></c:out><br>
Cloth Price : <c:out value="${c.price}"></c:out><br>
Cloth Brand : <c:out value="${c.brand}"></c:out><br>
<img alt="" src="getimage?cid=${c.cid}" height="70px" width="70px"><br>
<hr><hr>
</c:forEach>

</body>
</html>