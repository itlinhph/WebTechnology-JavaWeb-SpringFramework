<%-- 
    Document   : manageSupplier
    Created on : May 8, 2017, 10:51:59 PM
    Author     : linhphan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý nhà cung cấp</title>
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
                  <h1> Quản lý nhà cung cấp </h1>
                  <hr>
                  <div>
                      <div class="row">
                          <div class="col-md-5">
                              <h3> Danh sách nhà cung cấp: </h3>
                          </div>
                          <div class="col-md-4"></div>
                          <div class="col-md-3">
                            <a href="#modal-addCategory" data-toggle="modal" class="btn btn-lg btn-success">Thêm nhà cung cấp</a>
                          </div>
                      </div>
                      
                      
                      <div class="row">
                            <table class="table table-bordered" id="table-user">
                              <thead>
                                <tr>
                                  <th>ID </th>
                                  <th>Tên nhà cung cấp</th>
                                  <th>Địa chỉ</th>
                                  <th>Hành động</th>
                                </tr>
                              </thead>
                                <tbody>
                                <c:forEach var="s" items="${sList}">
                                <tr>

                                    <td> ${s.getsId()}  </td>
                                    <td> ${s.getsName()} </td>
                                    <td> ${s.getsAddress()} </td>
                                    <td> <a href="#modal-${s.getsId()}" data-toggle="modal" class="btn btn-success">Sửa</a>
                                         <a href="#delete-${s.getsId()}" data-toggle="modal" class="btn btn-danger">Xóa</a> </td>
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

      <c:forEach var="s" items="${sList}">
        <!-- modal-edit-->
        <div class="modal fade" id="modal-${s.getsId()}">
            <form action="processSupplier" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title"> Sửa nhà cung cấp: ${s.getsName()}</h4>
                   
                  </div>
                  <div class="modal-body">

                    <div class="form-group">
                      <label for="">Tên nhà cung cấp</label>
                      <input type="text" class="form-control" id="" value="${s.getsName()}" required="required" name="newName">
                    </div>
                    <div class="form-group">
                      <label for="">Địa chỉ</label>
                      <input type="text" class="form-control" id="" value="${s.getsAddress()}" required="required" name="newAddress">
                    </div>
                  </div>

                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                    <input type="hidden" name="command" value="update">
                    <input type="hidden" name="sId" value="${s.getsId()}">
                    <input type="submit" class="btn btn-primary" value="Hoàn tất" name="edit">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
        
        <!-- modal-delete-->
        <div class="modal fade" id="delete-${s.getsId()}">
            <form action="processSupplier" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Việc xóa nhà cung cấp sẽ ảnh hưởng tới các sản phẩm của nhà cung cấp này. Chắc chắn muốn xóa "${s.getsName()}" chứ ?</h4>
                   
                  </div>
                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Không chắc lắm</button>
                    <input type="hidden" name="command" value="delete">
                    <input type="hidden" name="curentId" value="${s.getsId()}">
                    <input type="submit" class="btn btn-primary" value="Ừ. Tao chắc" name="delete">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
      
      </c:forEach>
      
        <!-- modal-add-->
        <div class="modal fade" id="modal-addCategory">
            <form action="processSupplier" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Thêm nhà cung cấp</h4>
                   
                  </div>
                  <div class="modal-body">

                    <div class="form-group">
                      <label for="">Tên nhà cung cấp</label>
                      <input type="text" class="form-control"  placeholder="Tên nhà cung cấp" required="required" name="sName">
                    </div>
                    <div class="form-group">
                      <label for="">Địa chỉ</label>
                      <input type="text" class="form-control"  placeholder="Địa chỉ" required="required" name="sAddress">
                    </div>
                  </div>

                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                    <input type="hidden" name="command" value="add">
                    <input type="submit" class="btn btn-primary" value="Thêm mới" name="add">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
    </body>

</html>
