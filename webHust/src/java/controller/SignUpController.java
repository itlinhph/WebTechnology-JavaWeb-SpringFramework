/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import otherAddOn.Mail;

/**
 *
 * @author linhphan
 */
@Controller
@RequestMapping(value="/signup")
public class SignUpController {
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException {
        
        request.setCharacterEncoding("UTF-8");
        String url = request.getContextPath() + "/home" ;
        try{
            
            String username = request.getParameter("username-submit");
            String password = request.getParameter("password-submit");
            String repassword = request.getParameter("repassword-submit");
            String name = request.getParameter("name-submit");
            String email = request.getParameter("email-submit");
            String address = request.getParameter("address-submit");
            String phoneTemp = request.getParameter("phone-submit");
            int phone = Integer.parseInt(phoneTemp);
            
            if(User.checkUsername(username)) {
                mm.put("script", "<script> alert('Tài khoản đã tồn tại!');  window.location = '"+request.getContextPath()+"/home' </script>");
//                url = request.getContextPath() +"/home" ;
//                System.out.println("Vao day");
            }
            else {
                User userTemp = new User(username, password, name, email, address, phone);
                HttpSession session = request.getSession();
                session.setAttribute("userTemp", userTemp);
                url = request.getContextPath() + "/signup/verify" ;
                
                // Make code 4 digit
                int codeTemp = (int) Math.floor(Math.random()*8999 + 1000) ;
                String code = Integer.toString(codeTemp);
                session.setAttribute("code", code);
                
                // send mail
                String error = Mail.sendMail(email,"Xác nhận Email tại WEBHUST", "Xin chào "+ userTemp.getFullName() +"\nMã xác nhận của bạn là: " + code) ;
            
                if(error !=null) {
                   mm.put("script", "<script> alert(' Có lỗi khi gửi mail! Đăng ký không thành công!'); </script>") ;
                   url = request.getContextPath() + "/home" ;
                }
                
                response.sendRedirect(url);
                
            }
            
        }
        catch(Exception e) {
          mm.put("script", "<script> alert('Đã có lỗi từ phía máy chủ! Vui lòng thử lại sau!'); </script>");
          response.sendRedirect(request.getContextPath() + "/home");
            
        }
//        response.sendRedirect(url);
        
        return "jsp/index" ;
    }
    
    @RequestMapping(value="/verify", method=RequestMethod.GET)
    public String getVerify(HttpServletRequest request) {
        return "jsp/checkMail" ;
    }
    
    @RequestMapping(value="/verify" , method=RequestMethod.POST)
    public String verify(HttpServletRequest request, HttpServletResponse response, ModelMap mm) {
        
//        String url ="/home" ;
        HttpSession session = request.getSession();
        User userTemp = (User) session.getAttribute("userTemp");
        String code = (String) session.getAttribute("code") ;
        String codeSubmit = request.getParameter("code-submit") ;
        if(code.equals(codeSubmit)) {
            try {
                User user = UserData.addUser(userTemp.getUsername(), userTemp.getPassword(), userTemp.getFullName(), userTemp.getEmail(), userTemp.getAddress(), userTemp.getPhone()) ;
                session.setAttribute("user", user);
//                url = "index.jsp?result=signup-success" ;
                mm.put("script", "<script> alert('Đăng nhập thành công!'); window.location = '"+request.getContextPath()+"/home' </script>" ) ;
                
            } catch (Exception e) {
//                url = "checkEmail.jsp" ;
                mm.put("script", " window.location = '"+request.getContextPath()+"/signup/verify' " ) ;

            }
        }
        else {
//            url ="checkEmail.jsp?result=false-code" ;
                mm.put("script", "<script> alert('Nhập sai mã!'); window.location = '"+request.getContextPath()+"/signup/verify' </script> " ) ;

        }
//        response.sendRedirect(url);
     return "jsp/checkMail" ;   
    }
    
}
