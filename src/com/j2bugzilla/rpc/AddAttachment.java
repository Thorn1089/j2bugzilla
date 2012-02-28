package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.Attachment;
import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Comment;

public class AddAttachment implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.add_attachment";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	public AddAttachment(Attachment attachment, int id) {
		params.put("ids", id);
		params.put("data", attachment.getRawData());
		params.put("file_name", attachment.getFileName());
		params.put("summary", attachment.getSummary());
		params.put("content_type", attachment.getMIMEType());
	}
	
	public AddAttachment(Attachment attachment, Bug bug) {
		this(attachment, bug.getID());
	}
	
	public AddAttachment(Attachment attachment, Bug bug, String comment) {
		this(attachment, bug);
		params.put("comment", comment);
	}
	
	public AddAttachment(Attachment attachment, Bug bug, Comment comment) {
		this(attachment, bug, comment.getText());
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
