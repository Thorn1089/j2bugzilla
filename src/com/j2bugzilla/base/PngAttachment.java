package com.j2bugzilla.base;

/**
 * The {@code PngAttachment} class is a subtype of {@link Attachment} designed to decipher and display
 * PNG files which have been attached to Bugzilla.
 * 
 * @author Tom
 *
 */
public class PngAttachment extends Attachment {

	PngAttachment(String data, String name) {
		super(data, name);
		this.setMIMEType("image/png");
	}
	
	PngAttachment(String data, String name, int id, int bugId) {
		super(data, name, id, bugId);
		this.setMIMEType("image/png");
	}

}
