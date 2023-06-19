package in.vasanth.utility;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;



@Component
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;




	@Override
	public String sendSimpleMail(String recipient,String subject,String body) {
		try {
			SimpleMailMessage mailMessage=
					new SimpleMailMessage();
			//mailMessage.setFrom(sender);
			mailMessage.setTo(recipient);
			mailMessage.setSubject(subject);
			mailMessage.setText(body);
			
			javaMailSender.send(mailMessage);
			return "Mail sent successfully";
			
		}catch (Exception e) {
			return "Error while Sending Mail"; 
			
		}
		

		
	}

	@Override
	public String sendMailWithAttachment(String recipient,String subject,String body,String attachment,File file) {
		
		
		try {
			MimeMessage mimeMessage=
					javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper=
					new MimeMessageHelper(mimeMessage,true);
			
			mimeMessageHelper.setTo(recipient);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);
			
			
			mimeMessageHelper.addAttachment(attachment,file );
		
			javaMailSender.send(mimeMessage);
			
			return "Mail sent Successfully";
			
			
		} catch (Exception e) {
			return "Error while sending Mail!!";
			
		}
	}

}
