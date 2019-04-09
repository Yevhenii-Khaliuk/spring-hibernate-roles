<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kerana
  Date: 22.02.19
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
    <h3>Category name: <c:out value="${category.categoryName}"/></h3>
    <p>Description: <c:out value="${category.description}"/></p>
    <c:forEach items="${category.products}" var="p">
        <p>Product: <c:out value="${p.productName}"/> - Price: <c:out value="${p.price}"/></p>
    </c:forEach>

    <h6><a href="<c:url value="/reduce?c_id=${category.id}"/>">Reduce</a> 10%</h6>

</body>
</html>
