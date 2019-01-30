package com.spring.schedule;

import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.Address;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;

import java.util.HashMap;
import java.util.Properties;
import com.spring.schedule.MySMTPAuthenticator;

 
public class GoogleMail {
	
    public void sendmail(HashMap<String, String> rentalInfo)  // rentalInfo 받는 사람의 정보
    		throws Exception{
        
    	// 1. 정보를 담기 위한 객체
    	Properties prop = new Properties(); 
    	
    	// 2. SMTP 서버의 계정 설정
   	    //    Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정 
    	prop.put("mail.smtp.user", "tnprlfnqhr1@gmail.com");
        	
    	
    	// 3. SMTP 서버 정보 설정
    	//    Google Gmail 인 경우  smtp.gmail.com
    	prop.put("mail.smtp.host", "smtp.gmail.com");
         	
    	
    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.smtp.debug", "true");
    	prop.put("mail.smtp.socketFactory.port", "465");
    	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	prop.put("mail.smtp.socketFactory.fallback", "false");
    	
    	prop.put("mail.smtp.ssl.enable", "true");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      	
    	
    	Authenticator smtpAuth = new MySMTPAuthenticator();
    	Session ses = Session.getInstance(prop, smtpAuth);
    		
    	// 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
    	ses.setDebug(true);
    	        
    	// 메일의 내용을 담기 위한 객체생성
    	MimeMessage msg = new MimeMessage(ses);

    	// 제목 설정
    	String subject = "도서대여 반납기한";
    	msg.setSubject(subject);
    	        
    	// 보내는 사람의 메일주소
    	String sender = "tnprlfnqhr1@gmail.com";
    	Address fromAddr = new InternetAddress(sender);
    	msg.setFrom(fromAddr);
    	        
    	// 받는 사람의 메일주소
    	Address toAddr = new InternetAddress(rentalInfo.get("EMAIL"));
    	msg.addRecipient(Message.RecipientType.TO, toAddr);
    	        
    	// 메시지 본문의 내용과 형식, 캐릭터 셋 설정
    	msg.setContent( "<div style='margin-top: 30px;'>\n" + 
		    			"	<div class='container' style='border: 1px solid #377bb3; border-radius: 5px; width: 80%; margin: auto; padding-top: 0px;' align=\"center\">\n" + 
		    			"		<div class='row'>\n" + 
		    			"		\n" + 
		    			"			<div class='panel panel-primary'>\n" + 
		    			"				<div class='panel-heading' style='background-color: #377bb3;'>\n" + 
		    			"					<h3 class='panel-title' style='margin: 0px; padding-top: 5px; padding-bottom: 5px; vertical-align: middle;'>\n" + 
		    			"						<img src=\"https://cdn.icon-icons.com/icons2/621/PNG/512/magnifier-1_icon-icons.com_56924.png\" width=\"30px\" height=\"30px\"> ANANAS</span>\n" + 
		    			"					</h3>\n" + 
		    			"				</div>\n" + 
		    			"				<div class='panel-body' style='font-size: 15pt; margin-top: 20px; margin-bottom: 20px;'>\n" + 
		    			"					<span style=\"color: red; font-weight: bold;\">" + rentalInfo.get("MEMBERID") + "(" + rentalInfo.get("NAME") +
		    								")</span> 님의 <span style=\"color: red; font-weight: bold;\">" + rentalInfo.get("TITLE") + "</span>\n" + 
		    			"					이 반납 기한이 <span style=\"color: red; font-weight: bold;\">내일</span>까지입니다.<br/>\n" + 
		    			"					대여한 도서관에서 반납해주시기 바랍니다.\n" + 
		    			"				</div>\n" + 
		    			"\n" + 
		    			"			</div>\n" + 
		    			"			\n" + 
		    			"		</div>\n" + 
		    			"	</div>\n" + 
		    			"</div>", "text/html;charset=UTF-8");
    	        
    	// 메일 발송하기
    	Transport.send(msg);
    	
    }// end of sendmail(String recipient, String certificationCode)-----------------
    
    
}