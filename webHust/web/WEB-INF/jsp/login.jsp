<%-- 
    Document   : login
    Created on : Apr 13, 2017, 4:12:11 PM
    Author     : linhphan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 

    </head>
    ${script}
    <body>
        
       <jsp:include page="header.jsp"></jsp:include>
       
        <form action="${pageContext.request.contextPath}/log/login" method="POST" role="form">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Đăng nhập để tiếp tục</h4>
              </div>
              <div class="modal-body">

                <div class="form-group">
                  <label for="">Username</label>
                  <input type="text" class="form-control" id="" placeholder="Username" required="required" name="username1-submit">
                </div>
                <div class="form-group">
                  <label for="">Password</label>
                  <input type="password" class="form-control" id="" placeholder="Password" required="required" name="password1-submit">
                </div>
              </div>

              <div class="modal-footer">
                
                <a href ="#modal-signup" data-toggle="modal">Tạo tài khoản mới</a>
                <input type="submit" class="btn btn-primary" value="Đăng nhập" name="login">
              </div>
            </div>
          </div>
        </form>
    </body>
</html>
