<%-- 
    Document   : addProduct
    Created on : Apr 22, 2017, 9:41:54 AM
    Author     : linhphan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Thêm sản phẩm</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

        <script type ="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"> </script>

        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 
        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.css" rel="stylesheet">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.js"></script>
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
                <div class="col-md-10 well">
                    <h1> Quản lý Sản Phẩm </h1>
                    <hr>
                    <h3> <b> Thêm sản phẩm mới </b> </h3>
                    <br> <br>
                  <div class="box box-primary" style="margin: 0 auto; width:70%">
                       
                        <!-- form start -->
                        <form role="form" method="post" action="process-product?command=add" enctype="multipart/form-data">
                            <div class="box-body">
                               
                                <div class="form-group">
                                    <label>Mã sản phẩm</label>
                                    <input type="text" class="form-control" id="" name="productId" placeholder="Mã sản phẩm" required="required">
                                </div>
                                <div class="form-group">
                                    <label>Tên sản phẩm</label>
                                    <input type="text" class="form-control" id="" name="productName" placeholder="Tên sản phẩm" required="required">
                                </div>
                                
                                <div class="form-group">
                                    <label>Giá nhập</label>
                                    <input type="number" class="form-control" name="inputPrice" required="required">
                                </div>
                                    
                                <div class="form-group">
                                    <label>Giá bán</label>
                                    <input type="number" class="form-control" name="PPrice" required="required">
                                </div>
                                
                                <div class="form-group">
                                    <label>Loại sản phẩm</label>
                                    <select name="categoryId">
                                        <c:forEach var="c" items="${cList}">
                                            <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                    
                                <div class="form-group">
                                    <label>Hãng sản xuất</label>
                                    <select name="supplierId">
                                        <c:forEach var="s" items="${sList}">
                                            <option value="${s.getsId()}">${s.getsName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                    
                                <div class="form-group">
                                    <label>Thông số kĩ thuật</label>
                                    <textarea class="summernote" name="description" placeholder="Nhập mô tả..." required="required"></textarea>
                                </div>
                                    
                                <div class="form-group">
                                    <label>Ảnh</label>
                                    <input type="file" id="exampleInputFile" name="Img" required="required"> 
                                </div>
                                
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <!--<input type="hidden" name="command" value="add">-->
                                <button type="submit" class="btn btn-primary">Thêm mới</button>
                            </div>
                        </form>
                    </div>
                </div> <!-- End Prd review -->
              </div> <!-- End conntent -->
            
            </div> <!-- End container -->
       
        </div>
       <jsp:include page="../footer.jsp"></jsp:include>
        
    </body>
    <script>
        $(document).ready(function(){
            $(function() {
                $('.summernote').summernote();
            }); 
        }) ;
        
    </script>
</html>
