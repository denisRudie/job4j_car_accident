<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Accident</title>
</head>
<body>
<div class="container pt-3">
    <a class="btn btn-primary mb-2" href="<c:url value='/create'/>">Add accident</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
            <th scope="col">type</th>
            <th scope="col">rules</th>
            <th scope="col">description</th>
            <th scope="col">address</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td>
                    <c:out value="${accident.id}"/>
                </td>
                <td>
                    <c:out value="${accident.name}"/>
                </td>
                <td>
                    <c:out value="${accident.type.name}"/>
                </td>
                <td>
                    <c:forEach items="${accident.rules}" var="rule">
                        <p>
                            <c:out value="${rule.name}"/>
                        </p>
                    </c:forEach>
                </td>
                <td>
                    <c:out value="${accident.text}"/>
                </td>
                <td>
                    <c:out value="${accident.address}"/>
                </td>
                <td>
                    <a href="<c:url value="/edit?id=${accident.id}"/>">
                        <i class="fa fa-edit mr-3"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>