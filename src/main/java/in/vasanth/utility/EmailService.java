package in.vasanth.utility;

import java.io.File;

public interface EmailService {
	
	
	public String sendSimpleMail(String recipient,String subject,String body);
	public String sendMailWithAttachment(String recipient,String subject,String body,String attachment,File file);

}
