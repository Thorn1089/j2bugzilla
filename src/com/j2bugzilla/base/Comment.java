package com.j2bugzilla.base;

/**
 * The {@code Comment} class represents a comment entered for a particular
 * {@link Bug} in a Bugzilla installation. Each comment has a unique ID, and
 * is correlated with at most one {@code Bug}.
 * 
 * @author tgolden
 *
 */
public class Comment {

	/**
	 * The unique ID of this {@link Comment} on a Bugzilla installation
	 */
	private final int id;
	
	/**
	 * The message content of this {@link Comment}
	 */
	private final String text;
	
	/**
	 * Creates a new {@link Comment} from a Bugzilla installation
	 * @param id The unique ID of this comment
	 * @param text The text content of this comment
	 */
	public Comment(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	/**
	 * Creates a new {@link Comment} to be submitted to a Bugzilla installation
	 * @param text The text content of this comment
	 */
	public Comment(String text) {
		this.id = -1;
		this.text = text;
	}
	
	/**
	 * @return The unique ID of this {@link Comment}
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return The text content of this {@link Comment}
	 */
	public String getText() {
		return text;
	}
	
	
}
