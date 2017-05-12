/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.CategoryData;
import model.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhphan
 */

@Controller
//@RequestMapping(value="/home")
public class HomePageController {
    
    @RequestMapping(value="/home")
    public String index(ModelMap mm) throws SQLException, ClassNotFoundException {
        mm.put("productList", ProductData.getMostProduct()) ;
        mm.put("categoryList", CategoryData.getCategoryList() ) ;
        return "jsp/index" ;
    }
}
