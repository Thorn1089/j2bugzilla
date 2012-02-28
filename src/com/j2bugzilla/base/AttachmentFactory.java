package com.j2bugzilla.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code AttachmentFactory} is responsible for creating new {@link Attachment} objects based off of
 * data from either a Bugzilla installation or user input. It is responsible for determining the correct
 * {@code Attachment} subtype to instantiate.
 * 
 * @author Tom
 *
 */
public class AttachmentFactory {
	
	private boolean interrupt = false;
	
	private String mime;
	
	private String data;
	
	private String name;
	
	private int id = -1;

	private int bugID = -1;

	private String summary;

	private String creator;

	private Date created;

	private Date modified;
	
	/**
	 * Tells the {@link AttachmentFactory} to begin building a new {@link Attachment}. This method must
	 * be called before any of the {@code set} methods, and before {@link #createAttachment() createAttachment()}.
	 * @return A reference to the current factory, so that invocations can be chained fluently.
	 */
	public AttachmentFactory newAttachment() {
		if(interrupt) { throw new IllegalStateException("Already constructing a new attachment"); }
		interrupt = true;
		
		//Reset fields
		id = -1;
		bugID = -1;
		mime = null;
		data = null;
		name = null;
		summary = null;
		creator = null;
		created = null;
		modified = null;
		
		return this;
	}
	
	/**
	 * Sets the MIME type of the attachment to create.
	 * @param mime An Internet Media Type or MIME type.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setMime(String mime) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.mime = mime;
		return this;
	}
	
	/**
	 * Sets the data content, encoded as {@code Base64}, for the attachment.
	 * @param rawData A {@code String} of Base64 data.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setData(String rawData) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.data = rawData;
		return this;
	}
	
	/**
	 * Sets the name of the attachment file.
	 * @param fileName A {@code String} representing the original filename.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setName(String fileName) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.name = fileName;
		return this;
	}
	
	/**
	 * Sets the unique ID of the attachment within a Bugzilla installation.
	 * @param id A unique integer identifying the attachment.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setID(int id) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.id = id;
		return this;
	}
	
	/**
	 * Sets the unique ID of the parent {@link Bug} for an attachment within a Bugzilla installation.
	 * @param bugID A unique integer identifying the attachment.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setBugID(int bugID) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.bugID = bugID;
		return this;
	}
	
	/**
	 * Sets the summary of the attachment to create.
	 * @param summary A {@code String} describing the attachment.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setSummary(String summary) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.summary = summary;
		return this;
	}
	
	/**
	 * Sets the creator of the attachment being created.
	 * @param creator A {@code String} representing the email address of the uploader.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setCreator(String creator) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.creator = creator;
		return this;
	}
	
	/**
	 * Sets the {@link Date} the attachment was first created on.
	 * @param created A date describing when the attachment was uploaded.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setCreationDate(Date created) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.created = created;
		return this;
	}
	
	/**
	 * Sets the {@link Date} the attachment was last modified.
	 * @param modified A date describing when the attachment was last changed.
	 * @return A reference to the current {@link AttachmentFactory}.
	 */
	public AttachmentFactory setModifiedDate(Date modified) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.modified = modified;
		return this;
	}
	
	public Attachment createAttachment() {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		
		Attachment a;
		if(id != -1 && bugID != -1) {
			a = new Attachment(data, name, id, bugID);
		} else {
			a = new Attachment(data, name);
		}
		
		a.setMIMEType(mime);
		a.setSummary(summary);
		a.setCreator(creator);
		a.setCreationDate(created);
		a.setModifiedDate(modified);
		
		interrupt = false;
		return a;
	}
	
}
