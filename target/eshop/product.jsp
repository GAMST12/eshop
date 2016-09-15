<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="eshop.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <b>PRODUCT PAGE</b>
        <br/>id : ${product.id}
        <br/>//Scriplet
        <%--<br/>id : <%response.getWriter().write(((Product)request.getSession("product").getId()));%>--%>
        <br/>name : ${product.name}
        <br/><a href="/index.jsp">main page</a>

        <br/> <%-- Add quiz to bucket --%>
        <a href = "./productAddToBucket.do?id=${product.id}">Add this product to bucket </a>

        <hr/> <%-- Show products in bucket --%>
        <h2>Products in bucket</h2>
            <ul>
                <c:forEach var="productInBucket" items="${productsInBucket}">
                    <li>
                        <a href = "./product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>: =
                ${productInBucket.value}
                    </li>
                </c:forEach>
            </ul>
    </body>
</html>