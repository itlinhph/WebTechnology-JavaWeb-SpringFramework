/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;
import model.ArrayListFileItem;
import model.Bill;
import model.BillData;
import model.Category;
import model.CategoryData;
import model.ProductData;
import model.Products;
import model.Supplier;
import model.SupplierData;
import model.User;
import model.UserData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhphan
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
    
    // Kiểm tra xem người truy nhập có phải là 1 admin hay không
    // Đề phòng việc có người truy cập vào trang admin bằng đường dẫn /admin/
    private boolean isAdmin(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin") ;
        if(admin==null) {
            response.sendRedirect(request.getContextPath()+"/home");
            return false;
        }
        else {
            mm.put("admin", admin) ;
        }
        return true;
    }
    
    @RequestMapping(value="/welcome")
    public String getIndexAdmin(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException {
        isAdmin(request, response, mm) ;
        return "jsp/admin/adminIndex" ;
    }
    
    @RequestMapping(value="/manage-user")
    public String manageUser(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException {
        
        if(isAdmin(request, response, mm)) {
            ArrayList<User> userList = UserData.getUserList() ;
            mm.put("userList", userList) ;
        }
        return "jsp/admin/manageUser" ;
    }

    
    @RequestMapping(value="/deleteUser", method=RequestMethod.POST)
    public String deleteUser(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException {
        
        String curentId = request.getParameter("curentId");
        try{
            UserData.deleteUser(curentId) ;
        }
        catch(Exception e) {
            String error = e.getMessage().toString() ;
            mm.put("script", "<script> alert('Có lỗi khi xóa người dùng! " + error +" ') </script>") ;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/manage-user") ;
        
        return "jsp/admin/manageUser" ;
    }
        
    @RequestMapping(value="/manage-category")
    public String manageCategory(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException, SQLException, ClassNotFoundException {
        
        if(isAdmin(request, response, mm)) {
            ArrayList<Category> cList = CategoryData.getCategoryList() ;
            mm.put("cList", cList) ;
        }
        
        return "jsp/admin/manageCategory" ;
    }
    
    @RequestMapping(value="/edit-category", method=RequestMethod.POST)
    public String editCategory(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");
        
        try {
            switch(command) {
                case "update" :
                    String oldId = request.getParameter("oldId");
                    String newId = request.getParameter("newId");
                    String newName = request.getParameter("newName");
                    CategoryData.updateCategory(oldId, newId, newName) ;
                    break;
                    
                case "delete" :
                    String curentId = request.getParameter("curentId");
                    CategoryData.deleteCategory(curentId) ;
                    
                    break;
                    
                case "add" :
                    String categoryId = request.getParameter("categoryId");
                    String categoryName = request.getParameter("categoryName");
                    CategoryData.addCategory(categoryId, categoryName);
            }
            
        } catch (Exception e) {
            String error = e.getMessage().toString() ;
            mm.put("script", "<script> alert('Có lỗi: " + error +" ') </script>") ;
        }
        response.sendRedirect(request.getContextPath() + "/admin/manage-category") ;
        
        return "jsp/admin/manageCategory" ;
    }
    
    @RequestMapping(value="/manage-product")
    public String manageProduct(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException, SQLException, ClassNotFoundException {
        
        if(isAdmin(request, response, mm)) {

            String category ="";
            ArrayList<Category> cList = CategoryData.getCategoryList() ;
            ArrayList<Products> listProduct = new ArrayList();
            
            if(request.getParameter("order") != null) {
                listProduct = ProductData.getMostProduct();
                category ="Sản phẩm đang hot";
            }
            else if(request.getParameter("category")==null) {
                listProduct = ProductData.getProductList() ;
            }
            else {
                String categoryId = request.getParameter("category") ;
                listProduct = ProductData.getProductListByCategory(categoryId) ;
                category = CategoryData.getCategoryNameById(categoryId) ;
            }
            
            mm.put("pList", listProduct) ;
            mm.put("category", category) ;
            mm.put("cList", cList) ;
        }
        
        return "jsp/admin/manageProduct" ;
    }
    
    @RequestMapping(value="/add-product")
    public String getAddProduct(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException, SQLException, ClassNotFoundException {
        
        if(isAdmin(request, response, mm)) {
            ArrayList<Category> cList = CategoryData.getCategoryList() ;
            ArrayList<Supplier> sList = SupplierData.getSupplierList() ;
            mm.put("cList", cList) ;
            mm.put("sList", sList) ;
        }
        return "jsp/admin/addProduct" ;
    }
    
    @RequestMapping(value="/edit-product")
    public String getEditProduct(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws SQLException, ClassNotFoundException, IOException  {
        
        if(isAdmin(request, response, mm)) {
            String productId = request.getParameter("productId") ;
            Products p = ProductData.getProductByID(productId) ;
            ArrayList<Category> cList = CategoryData.getCategoryList() ;
            ArrayList<Supplier> sList = SupplierData.getSupplierList() ;
            
            mm.put("p", p) ;
            mm.put("cList", cList) ;
            mm.put("sList", sList) ;
            
        }
        return "jsp/admin/editProduct" ;
    }
    @RequestMapping(value="/process-product", method=RequestMethod.POST)
    public String processProduct(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        String url = request.getContextPath()+"/admin/manage-product" ;
        
        String path = request.getRealPath("/resources/images");
        DiskFileItemFactory d = new DiskFileItemFactory();
        ServletFileUpload uploader = new ServletFileUpload(d);
        
        String command = request.getParameter("command");

        
        try {
            switch(command) {
                case "edit" :
                    
                    ArrayListFileItem listField = new ArrayListFileItem((ArrayList<FileItem>) uploader.parseRequest(request));
                    String oldId = listField.getValue("oldId");
                    String productId = listField.getValue("productId") ;
                    String pName = new String(listField.getValue("productName").getBytes("iso-8859-1"), "UTF-8");
                    int inputPrice = Integer.parseInt(listField.getValue("inputPrice"))  ;
                    int pPrice = Integer.parseInt(listField.getValue("PPrice") )  ;
                    int sId = Integer.parseInt(listField.getValue("supplierId") )  ;
                    String cId = listField.getValue("categoryId") ;
                    String description = new String(listField.getValue("description").getBytes("iso-8859-1"), "UTF-8");
                    
                    
                    FileItem image = listField.getFile("Img");
                    String img = image.getName();
                    File file = new File(path+"/"+img);
                    if(!file.exists()){
                        image.write(file);
                    }

                   
                    Products pro = new Products(productId, pName, inputPrice, pPrice, img, sId, cId, description );
                    
                    String error = ProductData.updateProduct(oldId, pro) ;
                    if(error !=null) {
                        mm.put("script", "<script> alert('Có lỗi khi cập nhật! Lỗi:" + error +"') </script>");
                    }
//                    url = request.getContextPath()+"/admin/manage-product" ;
                    break;
                    
                case "delete" :
                    String pId = request.getParameter("pId");
                    String error1 = ProductData.deleteProduct(pId) ;
                    
                    if(error1 !=null) {
                        mm.put("script", "<script> alert('Có lỗi khi xóa! Lỗi:" + error1 +"') </script>");
                    }
                    break;
                    
                case "add" :
                    ArrayListFileItem listField1 = new ArrayListFileItem((ArrayList<FileItem>) uploader.parseRequest(request));
                    String oldId1 = listField1.getValue("oldId");
                    String productId1 = listField1.getValue("productId") ;
                    String pName1 = new String(listField1.getValue("productName").getBytes("iso-8859-1"), "UTF-8");
                    int inputPrice1 = Integer.parseInt(listField1.getValue("inputPrice"))  ;
                    int pPrice1 = Integer.parseInt(listField1.getValue("PPrice") )  ;
                    int sId1 = Integer.parseInt(listField1.getValue("supplierId") )  ;
                    String cId1 = listField1.getValue("categoryId") ;
                    String description1 = new String(listField1.getValue("description").getBytes("iso-8859-1"), "UTF-8");
                    
                    
                    FileItem image1 = listField1.getFile("Img");
                    String img1 = image1.getName();
                    File file1 = new File(path+"/"+img1);
                    if(!file1.exists()){
                        image1.write(file1);
                    }
                    
                    Products pro1 = new Products(productId1, pName1, inputPrice1, pPrice1, img1, sId1, cId1, description1 );
                    String error2 = ProductData.addProduct(pro1) ;
                    if(error2 !=null) {
                        mm.put("script", "<script> alert('Có lỗi khi xóa! Lỗi:" + error2 +"') </script>");
                    }
                    
                    
            }
            
            
        } catch (Exception e) {
            String error = e.getMessage().toString() ;
            mm.put("script", "<script> alert('Error: " + error +"') </script>");
            
            
        }
        response.sendRedirect(request.getContextPath()+"/admin/manage-product");
        return "jsp/admin/manageProduct" ;
    }
    
    @RequestMapping(value="/manage-supplier")
    public String manageSupplier(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException, SQLException, ClassNotFoundException {
        if(isAdmin(request, response, mm)) {
            ArrayList<Supplier> sList = SupplierData.getSupplierList() ;
            mm.put("sList", sList) ;
        }
        return "jsp/admin/manageSupplier" ;
    }
    
    @RequestMapping(value="/processSupplier", method=RequestMethod.POST)
    public String processSupplier(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");

        try {
            switch(command) {
                case "update" :
                    int sId = Integer.parseInt(request.getParameter("sId")) ;
                    String newName = request.getParameter("newName");
                    String newAddress = request.getParameter("newAddress");
                    SupplierData.updateSupplier(sId, newName, newAddress) ;
                    break;
                    
                case "delete" :
                    int curentId = Integer.parseInt(request.getParameter("curentId")) ;
                    SupplierData.deleteSupplier(curentId);
                    break;
                    
                case "add" :
                    String sName = request.getParameter("sName");
                    String sAddress = request.getParameter("sAddress");
                    SupplierData.addSupplier(sName, sAddress) ;
            }
            
            
        } catch (Exception e) {
            String error = e.getMessage() ;
            mm.put("script", "<script> alert('Có lỗi:" +error +"'); </script>");
        }
        response.sendRedirect(request.getContextPath()+"/admin/manage-supplier");
        return "jsp/admin/manageSupplier" ;
    }
    
    @RequestMapping(value="/manage-bill")
    public String getManageBill(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException, SQLException, ClassNotFoundException {
        if(isAdmin(request, response, mm)) {
            ArrayList<Bill> bList = BillData.getBillList();
            mm.put("bList", bList);
        }
        return "jsp/admin/manageBill" ;
    }
    
    @RequestMapping(value="/processBill", method=RequestMethod.POST)
    public String processBill(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");

        try {
            switch(command) {
//                case "update" :
//                    String oldId = request.getParameter("oldId");
//                    String newId = request.getParameter("newId");
//                    String newName = request.getParameter("newName");
//                    CategoryData.updateCategory(oldId, newId, newName) ;
//                    break;
                    
                case "cancel" :
                    String billId = request.getParameter("billId");
                    
                    BillData.deleteBill(billId) ;
                    break;
                    
            }
            
        } catch (Exception e) {
            String error = e.getMessage() ;
            mm.put("script", "<script> alert('Có lỗi:" +error +"'); </script>");
        }
        response.sendRedirect(request.getContextPath() +"/admin/manage-bill");
        return "jsp/admin/manageBill" ;
    }
}
