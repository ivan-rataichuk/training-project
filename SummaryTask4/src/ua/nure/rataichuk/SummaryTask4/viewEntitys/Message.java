package ua.nure.rataichuk.SummaryTask4.viewEntitys;

import ua.nure.rataichuk.SummaryTask4.entities.Entity;

/**
 * Message entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Message extends Entity{
	
	private static final long serialVersionUID = -6058436256149495388L;
	
	private String cause;
	
	private boolean error;
	
	

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	
}
