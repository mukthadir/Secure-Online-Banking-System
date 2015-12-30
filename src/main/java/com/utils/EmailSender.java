/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 *
 * @author Adel
 */
public class EmailSender {
    public static boolean sendMail(String from,String password,String message,String to){
           String host="smtp.gmail.com";
//    	String host= "smtp.live.com";
           Properties props=System.getProperties();
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",from);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port", 587);
//        props.put("mail.smtp.port", 587); 
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage (session);
        try{
        mimeMessage.setFrom(new InternetAddress(from));
        
        InternetAddress toAddress=new InternetAddress ();
        //for (int i=0;i<to.length;i++){
          toAddress =new InternetAddress(to);
        
       // }
        
         //for (int i=0;i<toAddress.length;i++){
         
         mimeMessage.setRecipient (RecipientType.TO, toAddress);
        
        // }
         
         mimeMessage.setSubject("mail using javamail api");
         mimeMessage.setText(message);
         Transport transport=session.getTransport("smtp");
         transport.connect(host,from,password);
         transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
         transport.close();
         return true;
        }catch(MessagingException me){
        me.printStackTrace();
        
        }
       
        
        return false;
}
}