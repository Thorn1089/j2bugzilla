package com.j2bugzilla.base;

import java.util.Date;

/**
 * The {@code Attachment} class defines certain shared properties of files attached to a bug report.
 * Subclasses of {@code Attachment} may provide additional fields and methods to display and manipulate
 * the specific attachment data.
 * 
 * @author Tom
 *
 */
public abstract class Attachment {
	
	private final String encodedData;
	
	private final String name;
	
	private String summary;
	
	private String mime;
	
	private String creator;
	
	private int id;
	
	private int bugId;
	
	private Date creationTime;
	
	private Date lastChange;
	
	Attachment(String data, String name) {
		encodedData = data;
		this.name = name;
	}
	
	Attachment(String data, String name, int id, int bugId) {
		this(data, name);
		this.id = id;
		this.bugId = bugId;
	}
	
	/**
	 * Returns the Base64-encoded content of this file attachment as a {@code String}.
	 * @return A {@code String} in Base64 format.
	 */
	public String getRawData() {
		return encodedData;
	}
	
	/**
	 * Returns the original file name of the attachment.
	 * @return A {@code String} representing the attachment's file name.
	 */
	public String getFileName() {
		return name;
	}
	
}
