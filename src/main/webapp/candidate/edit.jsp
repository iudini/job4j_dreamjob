<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script>
        function validate() {
            if ($('#name').val() === '') {
                alert($('#name').attr('title'));
                return false;
            }
        }
    </script>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/dreamjob/city',
                dataType: 'json'
            }).done(function (data) {
                data.forEach(el => {
                    document.querySelector('#city_id').append(new Option(el.name, el.id));
                })
            }).fail(function (err) {
                console.log(err);
            });
        });
    </script>
    <title>???????????? ??????????</title>
</head>
<header>
    <div class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="<%=request.getContextPath()%>" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            Home
        </a>
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">????????????????</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">??????????????????</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">???????????????? ????????????????</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">???????????????? ??????????????????</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">
                    <c:out value="${sessionScope.user.name}"/> | ??????????
                </a>
            </li>
        </ul>
    </div>
</header>
<body>
<%
    String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "", 0);
    if (id != null) {
        candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(id));
    }
%>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                ?????????? ????????????????.
                <% } else { %>
                ???????????????????????????? ??????????????????.
                <% } %>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                    <div class="form-group">
                        <label>??????</label>
                        <input type="text" id="name" class="form-control" name="name" value="<%=candidate.getName()%>" title="?????????????? ??????">
                    </div>
                    <div class="form-group">
                        <label for="city_id">??????????</label>
                        <select class="form-control" id="city_id" name="city_id"  title="???????????????? ??????????">
                            <option></option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">??????????????????</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>