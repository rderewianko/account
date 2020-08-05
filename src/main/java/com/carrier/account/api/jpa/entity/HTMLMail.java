package com.carrier.account.api.jpa.entity;

/**
 * MailSerice class to process mail subject and content.
 *
 */
public class HTMLMail {
	/**
	 * Default constructor.
	 */
	public HTMLMail() {
	}

	/**
	 * return subject for admin registration request.
	 *
	 * @return string
	 */
	public String getAdminSubject() {
		return "HVACpartners Registration Request";
	}

	/**
	 * return subject for user registration.
	 *
	 * @return String
	 */
	public String getUserSubject() {
		return "Thank you for your HVACpartners Registration Request";
	}

	/**
	 * return content for admin registration request.
	 *
	 * @param user user
	 * @return string
	 */
	public String getAdminContent(User user) {
		return "<html>" + "<body>"
				+ "<p>An HVACpartners registration has just been completed for the user listed below.</p>"
				+ "<p>Registrant's Name:" + " " + user.getFirstName() + " " + user.getLastName() + "<br>"
				+ "Company Name:" + " " + user.getCompany().getCompanyName() + "<br>"
				+ "Company ID:" + " " + user.getCompany().getCompanyId() + "</p>"
				+ "<p>Please logon to HVACpartners, go to \"Registration Admin\" and process the registration as appropriate within the next 1-2 days so the user can <br>"
				+ "begin to realize the great benefits of this site.</p>"
				+ "<p>Kind Regards,<br>"
				+ "Registration Processor</p>" + "</body>" + "</html>";
	}

	/**
	 * return content for user registration mail template.
	 *
	 * @param user user
	 * @return user
	 */
	public String getUserContent(User user) {
		return "<html>" + "<body>"
	            + "<p>Dear" + " " + user.getFirstName() + " " + user.getLastName() + "," + "</p>"
				+ "<p>Thank you for your interest in HVACpartners, Your Single Source for HVAC information. Your registration is currently being processed. <br>"
				+ "This process should take 1-2 days to complete, at which time you will receive a user ID and a temporary password to access the site.</p>"
				+ "<p>Kind Regards,<br>"
				+ "Registration Processor</p>" + "</body>" + "</html>";
	}
}
