package com.carrier.account;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *SpringBoot Servlet initializer class.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Loading resources.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AccountApplication.class);
	}

}
