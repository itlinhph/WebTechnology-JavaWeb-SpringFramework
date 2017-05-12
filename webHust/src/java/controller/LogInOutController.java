/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhphan
 */

@Controller
@RequestMapping(value="/log")
public class LogInOutController {
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String logIn(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            String username = request.getParameter("username1-submit");
            String password = request.getParameter("password1-submit");
            
            Admin admin = Admin.CheckAdmin(username,password);
            
            if(admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                response.sendRedirect(request.getContextPath()+"/admin/welcome") ;
                
            }
            else {
                
                User user = User.CheckLogin(username, password);
                if(user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user); 
                    response.sendRedirect(request.getContextPath()+"/home"); 
                }
                else {
                    mm.put("script", "<script> alert('Tài khoản hoặc mật khẩu không đúng!');  window.location = '"+request.getContextPath()+"/log/loginpage' </script>");
                }
            }
            
        }
        catch (Exception e) {
           response.sendRedirect(request.getContextPath()+"/home");
           
        }
        
        return "jsp/index" ;
    }
    
    @RequestMapping(value="/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath()+ "/home");
        
//        return null;
    }
    
    // Go to login page
    @RequestMapping(value="/loginpage", method=RequestMethod.GET)
    public String loginPage() {
        return "jsp/login" ;
    }
    
}
