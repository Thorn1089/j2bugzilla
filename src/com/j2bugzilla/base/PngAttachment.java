package com.j2bugzilla.base;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.ws.commons.util.Base64;

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
	}
	
	PngAttachment(String data, String name, int id, int bugId) {
		super(data, name, id, bugId);
	}
	
	public Image getImage() throws IOException {
		byte[] data = Base64.decode(this.getRawData());
		return ImageIO.read(new ByteArrayInputStream(data));
	}

}
