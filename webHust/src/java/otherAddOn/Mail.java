/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otherAddOn;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author linhphan
 */


public class Mail {
    
    public static String sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("linhphanhustwebsite@gmail.com", "linhphan");
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("linhphanhustwebsite@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            return e.getMessage().toString();
        }
        return null;
    }
    
//    public static void main(String[] args) {
//        System.out.println(sendMail("linhphanhust@gmail.com","Xác nhận đăng ký tài khoản","Bạn nhập mã để đăng ký tài khoản"));
//    }
}
