/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ArrayListFileItem;
import model.Cart;
import model.CategoryData;
import model.ProductData;
import model.Products;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author linhphan
 */

@Controller
@RequestMapping(value="/product")
public class ProductController {
    
    @RequestMapping(value="productList")
    public String getProductListByCategory(ModelMap mm, @RequestParam String category) throws SQLException, ClassNotFoundException {
        mm.put("productList", ProductData.getProductListByCategory(category)) ;
        mm.put("categoryList", CategoryData.getCategoryList()) ;
        mm.put("categoryId", category) ;
        return "jsp/index" ;
    }
    
    @RequestMapping(value="/detail") 
    public String getProductDetail(HttpServletRequest request, ModelMap mm, @RequestParam String productId) throws IOException, SQLException, ClassNotFoundException {
        Cart cart = (Cart) request.getSession().getAttribute("cart") ;
        mm.put("cart", cart) ;
        mm.put("product", ProductData.getProductByID(productId)) ;
        mm.put("categoryList", CategoryData.getCategoryList()) ;
        
    
        return "jsp/productDetail" ;
    }
}
