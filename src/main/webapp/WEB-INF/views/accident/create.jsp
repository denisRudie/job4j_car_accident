<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create accident</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>
<body class="container pt-3">
<form action="<c:url value='/save'/>" method='POST'>
    <div class="form-group">
        <label for="nameAccident">Accident name:</label>
        <input type="text" class="form-control" name="name" id="nameAccident" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="descriptionArea">Accident description:</label>
        <textarea class="form-control" name="text" id="descriptionArea" rows="3"></textarea>
    </div>
    <div class="form-group">
        <label for="addressAccident">Accident address:</label>
        <input type="text" class="form-control" name="address" id="addressAccident" placeholder="Enter address">
    </div>
    <input type="submit" class="btn btn-primary" value="Save"/>
</form>
</body>
</html>