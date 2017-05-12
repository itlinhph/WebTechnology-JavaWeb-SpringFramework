<%-- 
    Document   : checkMail
    Created on : Apr 29, 2017, 2:54:57 PM
    Author     : linhphan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kiểm tra Email</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 
    

            ${script}

    </head>

    <body>
    
        
       <jsp:include page="header.jsp"></jsp:include>
       
        <form action="../signup/verify" method="POST" role="form">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Một Email đã được gửi cho bạn. Hãy nhập code từ email để hoàn tất quá trình đăng ký!</h4>
              </div>
              <div class="modal-body">

                <div class="form-group">
                  <label for="">Nhập code</label>
                  <input type="text" class="form-control" id="" placeholder="code" required="required" name="code-submit">
                </div>
                
              </div>

              <div class="modal-footer">
                
                <input type="submit" class="btn btn-primary" value="Xác nhận" name="submit">
              </div>
            </div>
          </div>
        </form>
    </body>
</html>

