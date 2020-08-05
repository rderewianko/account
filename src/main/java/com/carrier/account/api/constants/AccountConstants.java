package com.carrier.account.api.constants;

/**
 * Class to creating query constants.
 *
 * @author Nanda.Kishore
 *
 */
public final class AccountConstants {
	/**
	 * Constructor to initialize.
	 */
	private AccountConstants() {
		throw new AssertionError();
	}

	/**
	 * Company lookup by companyCode select query.
	 */
	public static final String GET_COMPANY_BY_ID_SP = "{call p_Get_Company_By_ID_For_Self_Registration(?)}";
	/**
	 * Create user record in database.
	 */
	public static final String CREATE_USER_SP = "{call InsertSelfRegistrationRequests(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	/**
	 * User record inserted successfully.
	 */
	public static final String USER_SUCCESS_MESSAGE = "User record is successfully created";
	/**
	 * User Name available.
	 */
	public static final String USER_NAME_AVAILABLE = "User Name Available";
	/**
	 * Username already exists.
	 */
	public static final String USER_NAME_ALREADY_EXISTS = "Username already exists: ";
	/**
	 * Company lookup by zipcode select query.
	 */
	public static final String GET_COMPANY_BY_ZIPCODE_SP = "{call p_Get_Companies_By_Zipcode_Brand_CompanyType(?)}";
	/**
	 * Personalization settings record inserted successfully.
	 */
	public static final String PERSONALIZATION_SETTINGS_SUCCESS = "Personalization settings saved successfully";
	/**
	 * Delete User Details Stored procedure.
	 */
	public static final String DELETE_USER_DETAILS_SP = "DeleteUserDetail";
    /**
     * Gets Feature Id.
     */
	public static final String GET_FEATURE_ID_SP = "{call GetFeatureIdByBrandFamily(?)}";
	/**
	 * get supported brands for company.
	 */
	public static final String GET_BRANDS_FOR_COMPANY = "{call GetCompanyBrandsName(?)}";
	/**
	 * get company types for user.
	 */
	public static final String GET_COMAPNY_TYPES_OF_USER = "select uc.Id, ct.CompanyType,uc.UserID from UserCompanyTypesPersonalization uc"
			+ " Inner JOIN CompanyTypesPersonalization ct on ct.Id = uc.CompanyTypesId"
			+ " where uc.UserId = ? ";
	/**
	 * get company roles for user.
	 */
	public static final String GET_COMAPNY_ROLES_OF_USER = "select uc.Id, cr.CompanyRole,uc.UserID from UserCompanyRolesPersonalization uc"
			+ " Inner JOIN CompanyRolesPersonalization cr on cr.Id = uc.CompanyRolesId"
			+ " where uc.UserId = ? ";
	/**
	 * get assigned blackJack role for userId.
	 */
	public static final String GET_ASSIGNED_BLACKJACK_ROLE = "{call GetAssignedBlackjackRoleCodes(?)}";
	/**
	 * get pending self registration requests.
	 */
	public static final String  GET_SELF_REGISTRATION_REQUESTS = "{call p_Get_SelfRegistrationRequests(?,?,?)}";
    /**
     * Message to display if email already exists in DB.
     */
	public static final String EMAIL_ALREADY_EXISTS = "email already exists: ";
    /**
     * Message to display if email provided by user can be used in registration.
     */
	public static final String EMAIL_AVAILABLE = "Email Available";
}
