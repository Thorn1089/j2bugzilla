package com.j2bugzilla.base;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAttachmentFactory {

	@Test
	public void testAttachmentDefaults() {
		AttachmentFactory factory = new AttachmentFactory();
		Attachment attachment = factory.newAttachment().createAttachment();
		
		assertEquals("ID does not match", -1, attachment.getAttachmentID());
		assertEquals("Bug ID does not match", -1, attachment.getBugID());
		assertNull("MIME type is not null", attachment.getMIMEType());
		assertEquals("Data should be empty", 0, attachment.getRawData().length);
		assertNull("Name is not null", attachment.getFileName());
		assertNull("Summary is not null", attachment.getSummary());
		assertNull("Creator is not null", attachment.getCreator());
		assertNull("Creation date is not null", attachment.createdOn());
		assertNull("Modification date is not null", attachment.lastChangedOn());
	}

	@Test(expected = IllegalStateException.class)
	public void testImmutableState() {
		AttachmentFactory factory = new AttachmentFactory();
		factory.newAttachment().newAttachment();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testOrderOfOperations() {
		AttachmentFactory factory = new AttachmentFactory();
		factory.createAttachment();
	}
}
