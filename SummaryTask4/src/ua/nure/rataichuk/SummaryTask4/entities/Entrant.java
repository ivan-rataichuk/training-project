package ua.nure.rataichuk.SummaryTask4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Entrant entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Entrant extends Entity{
	
	private static final long serialVersionUID = -6025036256149495388L;
	
	private int userId;
	private String tel;
	private String email;
	private String certificateURL = "no file";
	private boolean isBlocked;
	
	private EntrantInfo ei;

	private List<EntrantInfo> eil;
	
	private List<Grade> gl;
	
	{
		eil = new ArrayList<>();
		gl = new ArrayList<>();
	}
	
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<Grade> getGl() {
		return gl;
	}
	public void setGl(List<Grade> gl) {
		this.gl = gl;
	}
	public EntrantInfo getInfo() {
		return ei;
	}
	public void setInfo(EntrantInfo ei) {
		this.ei = ei;
	}
	
	public List<EntrantInfo> getInfoList() {
		return eil;
	}
	public void setInfo(List<EntrantInfo> eil) {
		this.eil = eil;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCertificateURL() {
		return certificateURL;
	}
	public void setCertificateURL(String certificateURL) {
		this.certificateURL = certificateURL;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	
}
