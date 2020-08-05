
package com.carrier.account.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.jpa.entity.HTMLMail;
import com.carrier.account.api.jpa.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Mail service class to send mails for user and admin based on registration
 * request.
 *
 */
@Service("mail")
@Slf4j
public class MailService {
	/**
	 * Initializing smtpHost parameter.
	 */
	@Value("${smtpHostServer}")
	private String smtpHost;
	/**
	 * Initializing frommail parameter.
	 */
	@Value("${fromMail}")
	private String fromMail;
    /**
     * Autowires environment to get active profile.
     */
	@Autowired
	private Environment environment;

	/**
	 * method to send mail to admin on user registration request.
	 *
	 * @param mail mail
	 * @param user user
	 */
	public void sendAdminRegistrationMail(HTMLMail mail, User user) {

		try {
			log.info("Admin registration mail process started");
			MimeMessage msg = setMailproperties(); // set message headers
			msg.setSubject(mail.getAdminSubject(), "UTF-8");
			msg.setContent(mail.getAdminContent(user), "text/html");
			String[] activeProfiles = environment.getActiveProfiles();
			for (String profile : activeProfiles) {
				log.info("Profile Name:" + profile);
			}
			String toEmails;
			if (activeProfiles[0].equals("local") || activeProfiles[0].equals("Dev")) {
				toEmails = user.getMailIds().stream().collect(Collectors.joining(","));
				log.info("Reg Admin Mail Id's:" + toEmails);
				toEmails = "Gnanendra.M@gds.ey.com,Nanda.Kishore@gds.ey.com";
			} else {
				toEmails = user.getMailIds().stream().collect(Collectors.joining(","));
				log.info("Reg Admin Mail Id's:" + toEmails);
			}

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails, false));
			Transport.send(msg);

			log.info("Admin registration request mail sent successfully.");
		} catch (Exception ex) {
			log.error("Admin registration request mail exception : " + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occured sending Admin registartion request email");
		}
	}

	/**
	 * Method to send mail to user after registration.
	 *
	 * @param mail mail
	 * @param user user
	 */
	public void sendUserRegistrationMail(HTMLMail mail, User user) {

		try {
			log.info("user registration mail process started");
			MimeMessage msg = setMailproperties(); // set message headers
			msg.setSubject(mail.getUserSubject(), "UTF-8");
			msg.setContent(mail.getUserContent(user), "text/html");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmailAddress(), false));
			Transport.send(msg);

			log.info("User registration mail sent successfully.");
		} catch (Exception ex) {
			log.error("User registration mail exception :" + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occured sending user registartion email");
		}
	}

	/**
	 * method to set properties for mail template.
	 * @return MimeMessage
	 * @throws MessagingException MessagingException
	 * @throws UnsupportedEncodingException UnsupportedEncodingException
	 */
	private MimeMessage setMailproperties() throws MessagingException, UnsupportedEncodingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHost);

		Session session = Session.getInstance(props, null);
		MimeMessage msg = new MimeMessage(session); // set message headers
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress(fromMail, "NoReply-JD"));

		msg.setReplyTo(InternetAddress.parse(fromMail, false));
		msg.setSentDate(new Date());
		return msg;
	}

}
