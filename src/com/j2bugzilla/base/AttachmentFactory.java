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
	
	private static final Set<String> supportedImages = new HashSet<String>();
	
	static {
		supportedImages.add("images/gif");
		supportedImages.add("images/png");
		supportedImages.add("images/jpeg");
	}
	
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
	
	public AttachmentFactory setMime(String mime) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.mime = mime;
		return this;
	}
	
	public AttachmentFactory setData(String rawData) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.data = rawData;
		return this;
	}
	
	public AttachmentFactory setName(String fileName) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.name = fileName;
		return this;
	}
	
	public AttachmentFactory setID(int id) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.id = id;
		return this;
	}
	
	public AttachmentFactory setBugID(int bugID) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.bugID = bugID;
		return this;
	}
	
	public AttachmentFactory setSummary(String summary) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.summary = summary;
		return this;
	}
	
	public AttachmentFactory setCreator(String creator) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.creator = creator;
		return this;
	}
	
	public AttachmentFactory setCreationDate(Date created) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.created = created;
		return this;
	}
	
	public AttachmentFactory setModifiedDate(Date modified) {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		this.modified = modified;
		return this;
	}
	
	public Attachment createAttachment() {
		if(!interrupt) { throw new IllegalStateException("Need to call newAttachment() first"); }
		//Determine subtype
		Attachment a;
		if(isImageType(mime)) {
			if(id != -1 && bugID != -1) {
				a = new ImageAttachment(data, name, id, bugID);
			} else {
				a = new ImageAttachment(data, name);
			}
		} else {
			throw new IllegalArgumentException("Unsupported MIME type: " + mime);
		}
		a.setMIMEType(mime);
		a.setSummary(summary);
		a.setCreator(creator);
		a.setCreationDate(created);
		a.setModifiedDate(modified);
		
		interrupt = false;
		return a;
	}
	
	private boolean isImageType(String mime) {
		return supportedImages.contains(mime);
	}
	
}
