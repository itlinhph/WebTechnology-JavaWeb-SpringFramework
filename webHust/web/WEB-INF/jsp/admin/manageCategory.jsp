<%-- 
    Document   : manageCategory
    Created on : Apr 20, 2017, 2:13:02 PM
    Author     : linhphan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý danh mục</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 

    </head>
    ${script}
    <body>
        
        <jsp:include page="adminHeader.jsp"></jsp:include>
        <div class="container">
            <!-- Content -->
            <div class = "content">

              <div class="row">
                <div class="col-md-2 well">
                  <jsp:include page="navleft.jsp"></jsp:include>
                </div> <!-- End md-2 -->

                <!-- Product review-->
                <div class="col-md-9 well">
                  <h1> Quản lý danh mục </h1>
                  <hr>
                  <div>
                      <div class="row">
                          <div class="col-md-4">
                              <h3> Danh sách danh mục: </h3>
                          </div>
                          <div class="col-md-5"></div>
                          <div class="col-md-3">
                            <a href="#modal-addCategory" data-toggle="modal" class="btn btn-lg btn-success">Thêm danh mục mới</a>
                          </div>
                      </div>
                      
                      
                      <div class="row">
                            <table class="table table-bordered" id="table-user">
                              <thead>
                                <tr>
                                  <th>ID danh mục </th>
                                  <th>Tên danh mục</th>
                                  <th>Hành động</th>
                                </tr>
                              </thead>
                                <tbody>
                                
                                <c:forEach var="c" items="${cList}">
                                <tr>

                                    <td> ${c.getCategoryID()} </td>
                                    <td> ${c.getCategoryName()} </td>
                                    <td> <a href="#modal-${c.getCategoryID()}" data-toggle="modal" class="btn btn-success">Sửa</a>
                                         <a href="#delete-${c.getCategoryID()}" data-toggle="modal" class="btn btn-danger">Xóa</a> </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                  </div>
                  
                </div> <!-- End Prd review -->

              </div> <!-- End row -->
              
            </div>  <!-- End class content -->
            

        </div> <!-- End Container -->
        

      <div class = "footer"> 
        <p> Code by<a href="https://fb.com/deluxe.psk" target="_blank"> Linh Phan </a> </p>
      </div> <!-- End footer -->
        
        <c:forEach var="c" items="${cList}">
        
        <!-- modal-edit-->
        <div class="modal fade" id="modal-${c.getCategoryID()}">
            <form action="edit-category" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Sửa danh mục ${c.getCategoryName()}</h4>
                   
                  </div>
                  <div class="modal-body">

                    <div class="form-group">
                      <label for="">Mã danh mục mới</label>
                      <input type="text" class="form-control" id="" value="${c.getCategoryID()}" required="required" name="newId">
                    </div>
                    <div class="form-group">
                      <label for="">Tên danh mục mới</label>
                      <input type="text" class="form-control" id="" value="${c.getCategoryName()}" required="required" name="newName">
                    </div>
                  </div>

                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                    <input type="hidden" name="command" value="update">
                    <input type="hidden" name="oldId" value="${c.getCategoryID()}">
                    <input type="submit" class="btn btn-primary" value="Hoàn tất" name="edit">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
        
        <!-- modal-delete-->
        <div class="modal fade" id="delete-${c.getCategoryID()}">
            <form action="edit-category" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Việc xóa danh mục sẽ ảnh hưởng tới các sản phẩm trong danh mục này. Chắc chắn muốn xóa danh mục "${c.getCategoryName()}" chứ?</h4>
                   
                  </div>
                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Không chắc lắm</button>
                    <input type="hidden" name="command" value="delete">
                    <input type="hidden" name="curentId" value="${c.getCategoryID()}">
                    <input type="submit" class="btn btn-primary" value="Ừ. Tao chắc" name="edit">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
      
        </c:forEach>
      
        <!-- modal-add-->
        <div class="modal fade" id="modal-addCategory">
            <form action="edit-category" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Thêm danh mục</h4>
                   
                  </div>
                  <div class="modal-body">

                    <div class="form-group">
                      <label for="">Mã danh mục</label>
                      <input type="text" class="form-control"  placeholder="Mã danh mục" required="required" name="categoryId">
                    </div>
                    <div class="form-group">
                      <label for="">Tên danh mục</label>
                      <input type="text" class="form-control"  placeholder="Tên danh mục" required="required" name="categoryName">
                    </div>
                  </div>

                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                    <input type="hidden" name="command" value="add">
                    <input type="submit" class="btn btn-primary" value="Thêm mới" name="addCategory">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
    </body>

</html>
