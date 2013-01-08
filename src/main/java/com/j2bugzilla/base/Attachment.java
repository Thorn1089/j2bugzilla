/*
 * Copyright 2011 Thomas Golden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.j2bugzilla.base;

import java.util.Arrays;
import java.util.Date;

/**
 * The {@code Attachment} class defines certain shared properties of files attached to a bug report.
 * It provides the data from the posted attachment in the Base64 format for clients to transform as
 * appropriate, based on the MIME type.
 * 
 * There is one important exception -- Bugzilla 3.6 did not provide the actual binary data for
 * attachments. If an Attachment is created from a query on this version, only the metadata will be
 * returned.
 * 
 * @author Tom
 *
 */
public class Attachment {
	
	private final byte[] encodedData;
	
	private final String name;
	
	private String summary;
	
	private String mime;
	
	private String creator;
	
	private int id = -1;
	
	private int bugId = -1;
	
	private Date creationTime;
	
	private Date lastChange;
	
	Attachment(byte[] data, String name) {
		encodedData = data;
		this.name = name;
	}
	
	Attachment(byte[] data, String name, int id, int bugId) {
		this(data, name);
		this.id = id;
		this.bugId = bugId;
	}
	
	/**
	 * Returns the Base64-encoded content of this file attachment as an array of bytes.
	 * @return An array of bytes depicting Base64 data.
	 */
	public byte[] getRawData() {
		return Arrays.copyOf(encodedData, encodedData.length);
	}
	
	/**
	 * Returns the original file name of the attachment.
	 * @return A {@code String} representing the attachment's file name.
	 */
	public String getFileName() {
		return name;
	}
	
	/**
	 * Sets the Internet Media Type or MIME type of the {@link Attachment}.
	 * @param type A {@code String} representing a valid MIME type.
	 * @see http://en.wikipedia.org/wiki/Internet_media_type
	 */
	protected void setMIMEType(String type) {
		mime = type;
	}
	
	/**
	 * Returns the Internet Media Type or MIME type of the {@link Attachment}.
	 * @return A {@code String} representing a valid MIME type
	 * @see http://en.wikipedia.org/wiki/Internet_media_type
	 */
	public String getMIMEType() {
		return mime;
	}
	
	/**
	 * Returns the description of this {@link Attachment}, as provided by the
	 * initial poster.
	 * @return A {@code String} describing the file.
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Sets the summary of this {@link Attachment} to the provided value.
	 * @param summary The new summary for this attachment as a {@code String}.
	 */
	protected void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * Returns the name of the user who submitted this {@link Attachment}.
	 * @return The email address of the poster.
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Sets the creator of this {@link Attachment} to the provided email address.
	 * @param creator The uploader of this attachment.
	 */
	protected void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * Returns the unique ID of this attachment, or {@code -1} if the {@link Attachment} does not come
	 * from a Bugzilla installation.
	 * @return The integer ID of this file.
	 */
	public int getAttachmentID() {
		return id;
	}
	
	/**
	 * Returns the unique ID of the bug this attachment is posted on, or {@code -1} if the {@link Attachment} does
	 * not come from a Bugzilla installation.
	 * @return The integer ID of the parent {@link Bug}.
	 */
	public int getBugID() {
		return bugId;
	}
	
	/**
	 * Returns the date this {@link Attachment} was submitted to the installation.
	 * @return The {@link Date} when this attachment was posted.
	 */
	public Date createdOn() {
		return creationTime == null ? null : new Date(creationTime.getTime());
	}
	
	/**
	 * Sets the date this {@link Attachment} was created.
	 * @param created A {@link Date} representing when this attachment was submitted.
	 */
	protected void setCreationDate(Date created) {
		creationTime = created;
	}
	
	/**
	 * Returns the date this {@link Attachment} was last modified.
	 * @return The {@link Date} when this attachment was modified by a user.
	 */
	public Date lastChangedOn() {
		return lastChange == null ? null : new Date(lastChange.getTime());
	}
	
	/**
	 * Sets the date this {@link Attachment} was modifed on last.
	 * @param modified A {@link Date} representing when this attachment was last modified.
	 */
	protected void setModifiedDate(Date modified) {
		lastChange = modified;
	}
	
}
