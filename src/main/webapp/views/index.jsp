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
<h3 style="color: Black;">Clothes App</h3>
<hr>
<hr>
<a href="allclothes">View All Clothes here!</a>
<hr>
<hr>
<!-- <h3 style="color: black;">Update </h3> -->
<a href="updatepage">To Update Any Cloth Click here!</a>
<hr>
<hr>
<h3 style="color: Black;">For Viewing the Same Name Cloth Click here!</h3>
<a href="likename">View Here</a>
<hr>
<hr>
<h3 style="color: black;">Add Clothes</h3>
<c:if test="${result=='Successfully added'}">
<h3 style="color: green;">Successfully Added</h3>
</c:if>
<c:if test="${result=='failed'}">
<h3 style="color: red;">Something Went wrong!</h3>
</c:if>
<c:out value="${addresult}"></c:out>
<form action="AddClothes" method="post" enctype="multipart/form-data">
Cloth Id : <input type="number" name="cid" required/><br><hr>
Cloth Name : <input type ="text" name="name" required/><br><hr>
Cloth Price : <input type="number" name="price" required/><br><hr>
Cloth Brand : <input type="text" name="brand" required/><br><hr>
Cloth Image:<input type="file" name="image"/><br>
<button>Add</button>
</form>
<hr>
<hr>
<h3 style="color: Black;">Delete Cloth here</h3>
<c:if test="${OK=='Successfully Deleted'}">
<h3 style="color: green;">Successfully Deleted</h3>
</c:if>
<c:if test="${OK=='Error'}">
<h3 style="color: green;">Failed!</h3>
</c:if>
<form action="/delete">
Enter Cloth ID:<input type="number" name="cid" required/><br>
<button>Delete</button>
</form>
<hr>
<hr>
</body>
</html>