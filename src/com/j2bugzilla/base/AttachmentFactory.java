package com.j2bugzilla.base;

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
	
	public Attachment createAttachment() {
		//Determine subtype
		Attachment a;
		if("image/png".equals(mime)) {
			if(id != -1 && bugID != -1) {
				a = new PngAttachment(data, name, id, bugID);
			} else {
				a = new PngAttachment(data, name);
			}
		} else {
			throw new IllegalArgumentException("Unsupported MIME type: " + mime);
		}
		a.setSummary(summary);
		
		return a;
	}
	
}
