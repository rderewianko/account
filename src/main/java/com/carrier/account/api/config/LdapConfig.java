package com.carrier.account.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;

/**
 * @author Nanda.Kishore
 * LdapConfig that handles ldap connection details.
 */
@Configuration
@EnableLdapRepositories
public class LdapConfig {

	/**
	 * Configure ldapCredentials to ContextSource.
	 * @param contextSource contextSource
	 * @return LdapTemplate
	 */
	@Bean
	public LdapTemplate ldapTemplate(ContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}
}
