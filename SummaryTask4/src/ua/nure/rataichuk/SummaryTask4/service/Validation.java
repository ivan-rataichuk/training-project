package ua.nure.rataichuk.SummaryTask4.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.entities.Emessage;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;

/**
 * Validation service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class Validation {
	
	private static final Logger LOG = Logger.getLogger(Validation.class);
	
	/**
	 * Validate request parameters for entrant entity
	 * 
	 * @param request
	 * @return
	 */
	public  boolean entrant(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		for (String[] values : params.values()) {
			if (values[0].equals("")) {
				LOG.error("empty fields");
				return false;
			}
		}
		
		Set<String> keys = params.keySet();
		for (String s : keys) {

			
			if (s.contains("sub-") || s.contains("gradeId")) { 
				if (!checkGrade(request.getParameter(s))) {
					LOG.error("validation error subject");
					return false;
				}
			}
			if (s.contains("firstName")) {
				if (!checkString(request.getParameter(s)) || (request.getParameter(s).length() > 15)) {
					LOG.error("validation error firstName");
					return false;
				}
			}
			if (s.contains("middleName")) {
				if (!checkString(request.getParameter(s)) || (request.getParameter(s).length() > 15) ) {
					LOG.error("validation error middleName");
					return false;
				}
			}
			if (s.contains("lastName")) {
				if (!checkString(request.getParameter(s)) || (request.getParameter(s).length() > 15)) {
					LOG.error("validation error lastName");
					return false;
				}
			}
			
			if (s.contains("adress")) {
				if (request.getParameter(s).length() > 15) {
					LOG.error("validation error adress");
					return false;
				}
			}
			
			if (s.contains("oblast")) {
				if (request.getParameter(s).length() > 15) {
					LOG.error("validation error oblast");
					return false;
				}
			}
			
			if (s.contains("school")) {
				if (request.getParameter(s).length() > 15) {
					LOG.error("validation error school");
					return false;
				}
			}
			
			if (s.contains("tel")) {
				if (!checkTel(request.getParameter(s))) {
					LOG.error("validation error tel");
					return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * Validate request parameters for faculty entity
	 * 
	 * @param request
	 * @return
	 */
	public boolean faculty(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		for (String[] values : params.values()) {
			if (values[0].equals("")) {
				LOG.error("validation error");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validate request parameters for subject entity
	 * 
	 * @param request
	 * @return
	 */
	public boolean subject(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		for (String[] values : params.values()) {
			if (values[0].equals("")) {
				LOG.error("validation error");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validate EmailMessage fields
	 * 
	 * @param mes Emessage
	 * @return
	 */
	public boolean emailMessage(Emessage mes) {
		
		if (mes.getMailFrom() == null || mes.getMailFrom().equals("")) {
			LOG.error("validation error");
			return false;
		}
		if (mes.getMailTo() == null || mes.getMailTo().equals("")) {
			LOG.error("validation error");
			return false;
		}
		if (mes.getSubject() == null || mes.getSubject().equals("")) {
			LOG.error("validation error");
			return false;
		}
		if (mes.getText() == null || mes.getText().equals("")) {
			LOG.error("validation error");
			return false;
		}
		return true;
	}
	
	/**
	 * Validate User fields
	 * 
	 * @param u User
	 * @return
	 */
	public boolean user(HttpServletRequest request) {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if (login == null || login.equals("")) {
			LOG.error("validation error");
			return false;
		}
		
		if (password == null || password.equals("")) {
			LOG.error("validation error");
			return false;
		}
		
		if (email == null || email.equals("")) {
			LOG.error("validation error");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Validate User fields
	 * 
	 * @param u User
	 * @return
	 */
	public boolean file(HttpServletRequest request) {
		
		final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	    final int MAX_FILE_SIZE      = 1024 * 1024 * 5; // 40MB
	    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 10; // 50MB
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOG.error("validation error file");
            return false;
        }
		
		DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        upload.setFileSizeMax(MAX_FILE_SIZE);
        
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        try {
			List<FileItem> formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        return checkFileName(fileName);
                    }
                }
            }
			
		} catch (FileUploadException e) {
			LOG.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Generate validation error message 
	 * 
	 * @return
	 */
	public Message generateValidationError() {
		Message mes = new Message();
		mes.setError(true);
		mes.setCause(ResponseMessages.VALIDATION_ERROR);
		return mes;
	}
	
	private boolean checkString(String s) {
		String check = new String();
		String pattern = "((?<=\\s)|(?<=^))([\\S&&\\D]{2,})((?=\\s)|(?=$))";
		Pattern r = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
		s.trim();
		Matcher m = r.matcher(s);
		if (m.find()) {
			check = m.group(2);
			
		}
		if (check.equals(s)) {
			return true;
		}
		return false;
	}
	
	private boolean checkNumber(String s) {
		String check = new String();
		String pattern = "(\\d*)";
		Pattern r = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
		s.trim();
		Matcher m = r.matcher(s);
		if (m.find()) {
			check = m.group(1);
		}
		if (check.equals(s)){
			return true;
		}
		return false;
	}
	
	private boolean checkTel(String s) {
		String check = new String();
		String pattern = "([\\-\\+\\d]{12,15})";
		Pattern r = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
		s.trim();
		Matcher m = r.matcher(s);
		if (m.find()) {
			check = m.group(1);
		}
		if (check.equals(s)){
			return true;
		}
		return false;
	}
	
	private boolean checkFileName(String s) {
		String check = new String();
		String pattern = "(.*?\\.pdf)";
		Pattern r = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
		s.trim();
		Matcher m = r.matcher(s);
		if (m.find()) {
			check = m.group(1);
		}
		if (check.equals(s)){
			return true;
		}
		return false;
	}
	
	private boolean checkGrade(String s) {
		if (checkNumber(s) && (Integer.parseInt(s) <= 100)) {
			return true;
		}
		return false;
	}
}
