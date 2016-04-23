package my.blog.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Value("${mail.fromEmail}")
	private String from;


	@Autowired
	private JavaMailSender mailSender;

	/***
	 * @param to
	 * @param subject
	 * @param content
	 * @return true if the email was successfully sent, else returns false indicating something wrong with the email
	 */
	public boolean sendMail(String to, String subject, String content){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);

		try {
			mailSender.send(mailMessage);

		} catch (MailSendException e) {
			throw e;
		}


		return true;
	}
}
