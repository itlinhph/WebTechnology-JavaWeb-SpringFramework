/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoryData;
import model.User;
import model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhphan
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
    
    @RequestMapping(value="/user-infor")
    public String getUserInfor(HttpServletRequest request, ModelMap mm) throws SQLException, ClassNotFoundException {
        User user = (User) request.getSession().getAttribute("user") ;
        mm.put("user", user) ;
        mm.put("categoryList", CategoryData.getCategoryList() ) ;
        return "jsp/user/userInfo" ;
    }
    
    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String editProfile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, IOException {
        
        request.setCharacterEncoding("utf-8");
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        String address = request.getParameter("newAddress");
        int phone = Integer.parseInt(request.getParameter("newPhone")) ;
        String name = request.getParameter("newName");
        String email = request.getParameter("newEmail");
        String pass = request.getParameter("newpassword");
        User user = UserData.updateUser(userId, address, phone, email, name, pass ) ;
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() +"/user/user-infor");
        
        return "jsp/user/userInfo" ;
    }
    
}
