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
package com.j2bugzilla.rpc;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.Attachment;
import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Comment;

/**
 * The {@code AddAttachment} class allows clients to post new attachments to existing {@link Bug Bugs},
 * with an optional descriptive comment.
 * 
 * @author Tom
 *
 */
public class AddAttachment implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.add_attachment";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	/**
	 * Add a new {@link Attachment} to the bug specified by the ID.
	 * @param attachment An {@code Attachment} object with the file data to upload.
	 * @param id A unique integer identifying a {@link Bug}.
	 */
	public AddAttachment(Attachment attachment, int id) {
		params.put("ids", id);
		params.put("data", attachment.getRawData());
		params.put("file_name", attachment.getFileName());
		params.put("summary", attachment.getSummary());
		params.put("content_type", attachment.getMIMEType());
	}
	
	/**
	 * Add a new {@link Attachment} to the specified {@link Bug}.
	 * @param attachment An {@code Attachment} object with the file data to upload.
	 * @param bug The {@code Bug} to attach the file to.
	 */
	public AddAttachment(Attachment attachment, Bug bug) {
		this(attachment, bug.getID());
	}
	
	/**
	 * Add a new {@link Attachment} to the specified {@link Bug}, with a descriptive comment.
	 * @param attachment An {@code Attachment} object with the file data to upload.
	 * @param bug The {@code Bug} to attach the file to.
	 * @param comment A descriptive {@code String} comment.
	 */
	public AddAttachment(Attachment attachment, Bug bug, String comment) {
		this(attachment, bug);
		params.put("comment", comment);
	}
	
	/**
	 * Add a new {@link Attachment} to the specified {@link Bug}, with a descriptive {@link Comment}.
	 * @param attachment An {@code Attachment} object with the file data to upload.
	 * @param bug The {@code Bug} to attach the file to.
	 * @param comment A descriptive {@code Comment}.
	 */
	public AddAttachment(Attachment attachment, Bug bug, Comment comment) {
		this(attachment, bug, comment.getText());
	}
	
	/**
	 * Returns the ID of the submitted {@link Attachment}.
	 * @return An integer ID for the newly created attachment.
	 */
	public int getID() {
		if(hash.containsKey("attachments")) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> attachmentMap = (Map<Object, Object>)hash.get("attachments");
			Collection<Object> ids = attachmentMap.values();
			return (Integer)ids.iterator().next();
		}
		return -1;
	}
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
