<%--
    Document   : manageProduct
    Created on : Apr 21, 2017, 10:32:15 PM
    Author     : linhphan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý sản phẩm</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 

    </head>
    
    <body>
        ${script}
        <jsp:include page="adminHeader.jsp"></jsp:include>
        <div class="container">
            <!-- Content -->
            <div class = "content">

              <div class="row">
                <div class="col-md-2 well">
                  <jsp:include page="navleft.jsp"></jsp:include>
                </div> <!-- End md-2 -->
                
                <!-- Product review-->
                <div class="col-md-10 well">
                    <h1> Quản lý Sản Phẩm </h1>
                    <hr>
                    <div class="row" id="category">
                      <div class="dropdown col-md-3">
                        <button class="dropdown-toggle btn btn-group btn-info" data-toggle="dropdown" href="#">Sắp xếp theo danh mục</button>
                        <ul class="dropdown-menu">
                            <li><a href="manage-product">Toàn bộ sản phẩm</a></li>
                            <li><a href="manage-product?order=hot">Sản phẩm đang hot</a></li>
                            
                            <c:forEach var="c" items="${cList}">
                                <li><a href="manage-product?category=${c.getCategoryID()}"> ${c.getCategoryName()} </a></li>
                            </c:forEach>
                        </ul>
                      </div>
                      <div class="col-md-3">
                            <h4> <b> ${category} </b></h4>
                      </div>
                      <div class="col-md-3"> </div>
                      <div class="col-md-3" id="btn-addProduct">
                            <a href="add-product" class="btn btn-lg btn-success">Thêm sản phẩm mới</a>
                       </div>
                    </div>

                  <h3> Danh sách sản phẩm: </h3>
                  <div class="row">
                        <table class="table table-bordered" id="table-user">
                          <thead>
                            <tr>
                              <th>ID Sản Phẩm </th>
                              <th>Tên Sản phẩm</th>
                              <th>Danh mục</th>
                              <th align="right">Giá gốc (đ)</th>
                              <th align="right">Giá bán (đ)</th>
                              <th>Hãng sản xuất</th>
                              <th>Xuất xứ</th>
                              <th>Hành động</th>
                            </tr>
                          </thead>
                            <tbody>
                            
                            <c:forEach var="product" items="${pList}">
                            <tr>
                                <td> ${product.getPID()} </td>
                                <td> ${product.getPName()} </td>
                                <td> ${product.getCategoryName()} </td>
                                <td align="right"> ${product.getInputPrice()}</td>
                                <td align="right"> ${product.getPPirce()}</td>
                                <td> ${product.getSupplyName()} </td>
                                <td> ${product.getSupplyAddress()} </td>
                                <td> 
                                    <a href="edit-product?productId=${product.getPID()}" class="btn btn-success">Sửa</a> 
                                    <a href="#delete-${product.getPID()}" data-toggle="modal" class="btn btn-danger">Xóa</a>
                                </td>

                            </tr>
                            </c:forEach>
                            </tbody>
                            
                        </table>
                    </div> <!-- End row -->
                </div> <!-- End Prd review -->
            </div> <!-- End conntent -->
            
        </div> <!-- End container -->
        
        <c:forEach var="product" items="${pList}">
       <!-- modal-delete-->
        <div class="modal fade" id="delete-${product.getPID()}">
            <form action="edit-product" method="POST" role="form">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Việc xóa sản phẩm sẽ ảnh hưởng tới hóa đơn liên quan đến sản phẩm này. Chắc chắn muốn xóa "${product.getPName()}" chứ?</h4>
                   
                  </div>
                  <div class="modal-footer">
                   
                    <button type="button" class="btn btn-default" data-dismiss="modal">Không chắc lắm</button>
                    <input type="hidden" name="command" value="delete">
                    <input type="hidden" name="pId" value="${product.getPID()}" >
                    <input type="submit" class="btn btn-primary" value="Ừ. Tao chắc" name="Delete">
                  </div>
                </div>
              </div>
            </form>
        </div> <!-- End modal -->
        </c:forEach>
      
       <jsp:include page="../footer.jsp"></jsp:include>
        
    </body>
</html>
