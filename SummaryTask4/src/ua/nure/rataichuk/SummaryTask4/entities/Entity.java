package ua.nure.rataichuk.SummaryTask4.entities;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * 
 * @author Ivan Rataichuk
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8466257860808346236L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
