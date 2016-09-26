package ua.nure.rataichuk.SummaryTask4.dao;

/**
 * Holder for  DB queries.
 * 
 * @author Ivan Rataichuk
 * 
 */
public final class Queries {

	public static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=? AND password=?";
	
	public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";

	public static final String FIND_ENTRANT_BY_ID = "SELECT * FROM entrants "
													 + "JOIN entrants_info ON entrants_info.entrant_id = entrants.id "
													 + "WHERE entrants.id=? AND locale_id=?";
	
	public static final String FIND_ENTRANT_INFO_INF_BY_ID = "SELECT * FROM entrants_info WHERE entrant_id=?";

	public static final String FIND_ENTRANT_INFO_E_BY_ID = "SELECT * FROM entrants WHERE id=?";
	
	public static final String UPDATE_ENTRANT = "UPDATE entrants SET email=?, tel=? WHERE id=?";
	
	public static final String UPDATE_ENTRANT_CERTIFICATE = "UPDATE entrants SET cetificate_url=? WHERE id=?";
	
	public static final String UPDATE_ENTRANT_INFO = "UPDATE entrants_info "
													 + "SET first_name=?, middle_name=?, last_name=?, adress=?, oblast=?, school=? "
													 + "WHERE entrant_id=? AND locale_id=?";
	
	public static final String UPDATE_GRADES = "UPDATE grades SET grade=? WHERE id=?";
	
	public static final String FIND_ALL_ENTRANTS = "SELECT * FROM entrants "
													+ "JOIN entrants_info ON entrants_info.entrant_id = entrants.id "
													+ "WHERE locale_id=?";

	public static final String FIND_ENTRANT_BY_USER_ID = "SELECT * FROM entrants "
														  + "JOIN entrants_info ON entrants_info.entrant_id = entrants.id "
														  + "WHERE entrants.user_id=? AND entrants_info.locale_id=?";

	public static final String FIND_GRADES_BY_ENTRANT_ID = "SELECT * FROM grades "
															+ "JOIN subject_info ON subject_info.subject_id = grades.subject_id "
															+ "WHERE grades.entrant_id=? AND locale_id=?";

	public static final String FIND_ALL_GRADES = "SELECT * FROM grades "
												  + "JOIN subject_info ON subject_info.subject_id = grades.subject_id "
												  + "WHERE locale_id=?";

	public static final String FIND_ALL_LOCALES = "SELECT lang FROM locale" ;
	
	public static final String CREATE_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?)";

	public static final String CREATE_GRADE = "INSERT INTO grades VALUES (DEFAULT, ?, ?, ?)";

	public static final String CREATE_REGISTRATION = "INSERT INTO registration VALUES (DEFAULT, ?, ?, ?, ?, ?, 0, 0, 0)";

	public static final String CREATE_ENTRANT = "INSERT INTO entrants VALUES (DEFAULT, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_ENTRANT_BLOCKING = "UPDATE entrants SET is_blocked=? WHERE id=?"; 

	public static final String CREATE_POSITIONS = "INSERT INTO positions VALUES (DEFAULT, ?, ?, ?, 0, 0)";

	public static final String CREATE_FACULTY = "INSERT INTO facultys VALUES (DEFAULT, ?, ?, ?)";

	public static final String DELETE_FACULTY = "DELETE FROM faculty_info, facultys, positions " 
												+ "USING faculty_info, facultys, positions "
												+ "WHERE faculty_info.faculty_id = facultys.id "
												+ "AND positions.faculty_id = facultys.id "
												+ "AND faculty_info.faculty_id=?";

	public static final String DELETE_REGISTRATION = "DELETE FROM registration WHERE id=?";
	
	public static final String BLOCK_REGISTRATION = "UPDATE registration SET is_blocked = true WHERE id!=? AND entrant_id=?";
	
	public static final String CHECK_REGISTRATION = "UPDATE registration SET is_checked = true, is_budget=? WHERE id=?";
	
	public static final String RESET_REGISTRATION = "UPDATE registration "
													+ "SET is_blocked = false, is_checked = false "
													+ "WHERE entrant_id=?";

	public static final String CREATE_FACULTY_INFO = "INSERT INTO faculty_info VALUES (DEFAULT, ?, ?, ?, ?)";

	public static final String CREATE_SUBJECT = "INSERT INTO subjects VALUES (DEFAULT)";

	public static final String CREATE_SUBJECT_INFO = "INSERT INTO subject_info VALUES (DEFAULT, ?, ?, ?)";

	public static final String CREATE_ENTRANT_INFO = "INSERT INTO entrants_info VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String FIND_LOCALE_ID = "SELECT * FROM locale WHERE lang=?";
	
	public static final String FIND_LOCALE_LANG = "SELECT * FROM locale WHERE id=?";

	public static final String FIND_ROLE_ID = "SELECT * FROM roles WHERE name=?";

	public static final String FIND_ALL_POSITIONS = "SELECT * FROM positions";

	public static final String FIND_ALL_REGISTRATIONS_BY_ID = "SELECT * FROM registration "
															   + "JOIN faculty_info ON faculty_info.faculty_id = registration.faculty_id "
															   + "WHERE registration.entrant_id=? AND faculty_info.locale_id=?";

	public static final String FIND_ALL_REGISTRATIONS = "SELECT * FROM registration "
														 + "JOIN faculty_info ON faculty_info.faculty_id = registration.faculty_id "
														 + "WHERE faculty_info.locale_id=?";

	public static final String FIND_ALL_SUBJECTS = "SELECT * FROM subject_info WHERE locale_id=?";

	public static final String FIND_ALL_FACULTYS = "SELECT facultys.*, faculty_info.*, positions.*, "
													+ " msi.name AS ms_name, ssi.name AS ss_name, tsi.name AS ts_name"
													+ " FROM facultys "
													+ " JOIN positions ON positions.faculty_id = facultys.id"
													+ " JOIN faculty_info ON faculty_info.faculty_id = facultys.id" 
													+ " JOIN subject_info msi ON msi.subject_id = facultys.ms_id"
													+ " JOIN subject_info ssi ON ssi.subject_id = facultys.ss_id"
													+ " JOIN subject_info tsi ON tsi.subject_id = facultys.ts_id"
													+ " WHERE faculty_info.locale_id=? AND msi.locale_id=? AND ssi.locale_id=? AND tsi.locale_id=?";
	
	public static final String FIND_FACULTY_BY_ID = "SELECT facultys.*, faculty_info.*, positions.*, "
													+ " msi.name AS ms_name, ssi.name AS ss_name, tsi.name AS ts_name"
													+ " FROM facultys "
													+ " JOIN positions ON positions.faculty_id = facultys.id"
													+ " JOIN faculty_info ON faculty_info.faculty_id = facultys.id" 
													+ " JOIN subject_info msi ON msi.subject_id = facultys.ms_id"
													+ " JOIN subject_info ssi ON ssi.subject_id = facultys.ss_id"
													+ " JOIN subject_info tsi ON tsi.subject_id = facultys.ts_id"
													+ " WHERE faculty_info.locale_id=? AND msi.locale_id=? AND ssi.locale_id=? AND tsi.locale_id=?"
													+ " AND facultys.id=?";

	
	public static final String UPDATE_FACULTY = "UPDATE facultys "
												+ "JOIN positions ON positions.faculty_id = facultys.id "
												+ "SET facultys.ms_id=?, facultys.ss_id=?, facultys.ts_id=?, "
												+ "positions.pos_quantity=?, positions.bud_pos_quantity=? "
												+ "WHERE facultys.id=?";
	
	public static final String UPDATE_FACULTY_INFO = "UPDATE faculty_info "
			+ "SET name=?, description=? "
			+ "WHERE faculty_id=? AND locale_id=?";

	public static final String UPDATE_POSITIONS = "UPDATE positions"
												  + " SET positions.pos_quantity=?, positions.bud_pos_quantity=?" + "	WHERE faculty_id=?";
	
	public static final String UPDATE_FILLED_POSITIONS_INC = "UPDATE positions"
			   												 + " SET pos_filled = pos_filled + 1 WHERE faculty_id=?";
	
	public static final String UPDATE_FILLED_POSITIONS_DEC = "UPDATE positions"
				 											 + " SET pos_filled = pos_filled - 1 WHERE positions.faculty_id = "
				 											 + "(SELECT faculty_id FROM registration WHERE id=?)";

	public static final String FIND_REPORT_ENTRYS = "SELECT registration.id, registration.entrant_id, registration.faculty_id, faculty_info.name AS faculty_name, " + 
													"entrants_info.first_name, entrants_info.middle_name, entrants_info.last_name, " + 
													"mg.grade AS grade_m, sg.grade AS grade_s, tg.grade AS grade_t, registration.is_blocked, " + 
												    "registration.is_checked, registration.is_budget " +
													"FROM registration " + 
													"JOIN entrants ON entrants.id = registration.entrant_id " +
													"JOIN entrants_info ON entrants_info.entrant_id = registration.entrant_id " +
													"JOIN faculty_info ON faculty_info.faculty_id = registration.faculty_id " +
													"JOIN grades mg ON mg.id = registration.mg_id " +
													"JOIN grades sg ON sg.id = registration.sg_id " + 
													"JOIN grades tg ON tg.id = registration.tg_id " + 
													"WHERE entrants_info.locale_id = ? AND faculty_info.locale_id = ? AND entrants.is_blocked = false";

}