<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form1.css">
</head>
<header>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</header>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input hidden name="command" value="block_user">
    <div>
        <c:choose>
            <c:when test="${requestScope.block != null}">
                <span style="color:green;">
                        ${block}
                </span>
            </c:when>
            <c:when test="${requestScope.error != null}">
                <span style="color: red">
                        ${error}
                </span>
            </c:when>
        </c:choose>
    </div>
    <div>
        <input type="text" name="user_name" id="user_name">
        <label for="user_name"><fmt:message key="label.password"/></label>
        <div class="requirements"></div>
    </div>
    <button type="submit"><fmt:message key="label.save"/> </button>
</form>
</body>
</html>
