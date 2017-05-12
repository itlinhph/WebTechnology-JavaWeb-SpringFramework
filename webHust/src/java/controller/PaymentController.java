/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BillDetail;
import model.Cart;
import model.Item;
import model.User;
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
@RequestMapping(value="/payment")
public class PaymentController {
    
    @RequestMapping(value="/get-infor", method=RequestMethod.GET)
    public String getPayment(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws IOException {
        
        User user = (User) request.getSession().getAttribute("user") ;
        if(user == null) {
            response.sendRedirect("../log/loginpage");
        }
        else {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            mm.put("cart", cart) ;
        }
        return "jsp/payment" ;
    }
    
    @RequestMapping(value="/send-infor", method=RequestMethod.POST)
    public String sendPayment(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException {
        
        request.setCharacterEncoding("UTF-8");
        String recieverName = request.getParameter("receiver-name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String payOption = request.getParameter("pay-option");
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        
        int totalMoney = cart.totalMoney() ;
        int userId = user.getUserID();
        Timestamp time = new Timestamp(new Date().getTime() ) ;
        
        try {
            
            Bill bill = new Bill(userId, totalMoney, recieverName, address, payOption, time);
            
            for(Map.Entry<String, Item> listItem : cart.getCartItems().entrySet()) {
                int billId = bill.getBillId();
                String productId = listItem.getValue().getProduct().getPID() ; 
                int Pprice = listItem.getValue().getProduct().getPPirce() ;
                int quantity = listItem.getValue().getQuantity() ;
                
                BillDetail billDetail = new BillDetail(billId, productId, Pprice, quantity);
                    
            }
            // Send mail
            String error = Mail.sendMail(user.getEmail(),"Hóa đơn tại WEBHUST", "Xin chào "+ user.getFullName() +"\nBạn đã gửi yêu cầu thanh toán 1 đơn hàng tại WEBHUST. Chi tiết:" + "\nMã hóa đơn: "+bill.getBillId() + "\nTổng số tiền: "+ totalMoney + " Đồng" + "\n\nCảm ơn bạn đã mua hàng tại WEBHUST!") ;
            
            if(error !=null) {
               mm.put("script", "<script> alert('Thanh toán đơn hàng thành công! Tuy nhiên đã có lỗi khi chúng tôi gửi mail cho bạn thông tin về đơn hàng này!');  window.location = '"+request.getContextPath()+"/home' </script>");
            }
            else {
                mm.put("script", "<script> alert('Thanh toán thành công! Kiểm tra email để xem lại thông tin về đơn hàng!');  window.location = '"+request.getContextPath()+"/home' </script>");
            }
            cart = new Cart();
            session.setAttribute("cart", cart) ;
        }
        catch (Exception e) {
            mm.put("script", "<script> alert('Đã có lỗi khi thanh toán đơn hàng!');  window.location = '"+request.getContextPath()+"/home' </script>");
            
        }
        
        return "jsp/payment" ;
    }
}
