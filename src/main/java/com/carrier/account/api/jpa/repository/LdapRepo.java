/**
 *
 */
package com.carrier.account.api.jpa.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.LdapUser;

/**
 * @author Nanda.Kishore
 * This class uses spring in built JPA features for ldap.
 */
@Repository
public interface LdapRepo extends LdapRepository<LdapUser> {

}
