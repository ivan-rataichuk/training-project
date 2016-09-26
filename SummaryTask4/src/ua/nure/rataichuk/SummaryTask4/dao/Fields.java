package ua.nure.rataichuk.SummaryTask4.dao;

/**
 * Holder for fields names of DB tables and entities.
 * 
 * @author Ivan Rataichuk
 * 
 */
public final class Fields {
	

	public static final String ENTITY_ID = "id";
	
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_EMAIL = "email";
	public static final String USER_ROLE_ID = "role_id";
	
	public static final String SUBJECT_ID = "subject_id";
	public static final String SUBJECT_NAME = "name";
	public static final String SUBJECT_LOCALE_ID = "locale_id";
	
	public static final String FACULTY_ID = "faculty_id";
	public static final String FACULTY_NAME = "name";
	public static final String FACULTY_DESCRIPTION = "description";
	public static final String FACULTY_LOCALE_ID = "locale_id";
	public static final String FACULTY_MS_ID = "ms_id";
	public static final String FACULTY_SS_ID = "ss_id";
	public static final String FACULTY_TS_ID = "ts_id";
	public static final String FACULTY_POS = "pos_quantity";
	public static final String FACULTY_BUD_POS = "bud_pos_quantity";
	public static final String FACULTY_POS_F = "pos_filled";
	public static final String FACULTY_BUD_POS_F = "bud_pos_filled";
	public static final String FACULTY_MS_NAME = "ms_name";
	public static final String FACULTY_SS_NAME = "ss_name";
	public static final String FACULTY_TS_NAME = "ts_name";
	
	public static final String ENTRANT_BLOCKED = "is_blocked";
	public static final String ENTRANT_USER_ID = "user_id";
	public static final String ENTRANT_EMAIL = "email";
	public static final String ENTRANT_TEL = "tel";
	public static final String ENTRANT_CERTIFICATE = "cetificate_url";
	public static final String ENTRANT_F_NAME = "first_name";
	public static final String ENTRANT_M_NAME = "middle_name";
	public static final String ENTRANT_L_NAME = "last_name";
	public static final String ENTRANT_ADRESS = "adress";
	public static final String ENTRANT_OBLAST = "oblast";
	public static final String ENTRANT_SCHOOL = "school";
	public static final String ENTRANT_LOCALE = "locale_id";
	
	public static final String GRADE_ENTRANT_ID = "entrant_id";
	public static final String GRADE_SUBJECT_ID = "subject_id";
	public static final String GRADE_GRADE = "grade";
	
	public static final String REG_ENTRANT_ID = "entrant_id";
	public static final String REG_FACULTY_ID = "faculty_id";
	public static final String REG_M_GRADE_ID = "mg_id";
	public static final String REG_S_GRADE_ID = "sg_id";
	public static final String REG_T_GRADE_ID = "tg_id";
	
	public static final String RE_ENTRANT_ID = "entrant_id";
	public static final String RE_FACULTY_ID = "faculty_id";
	public static final String RE_FACULTY_NAME = "faculty_name";
	public static final String RE_BLOCKED = "is_blocked";
	public static final String RE_CHECKED = "is_checked";
	public static final String RE_BUDGET = "is_budget";
	public static final String RE_FIRST_NAME = "first_name";
	public static final String RE_MIDDLE_NAME = "middle_name";
	public static final String RE_LAST_NAME = "last_name";
	public static final String RE_M_GRADE = "grade_m";
	public static final String RE_S_GRADE = "grade_s";
	public static final String RE_T_GRADE = "grade_t";
	
	
}