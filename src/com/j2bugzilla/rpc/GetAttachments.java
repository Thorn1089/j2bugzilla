package com.j2bugzilla.rpc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j2bugzilla.base.Attachment;
import com.j2bugzilla.base.AttachmentFactory;
import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;

/**
 * The {@code GetAttachments} class allows clients to retrieve {@link Attachment} objects from existing
 * {@link Bug Bugs} within a Bugzilla installation.
 * @author Tom
 *
 */
public class GetAttachments implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.attachments";
	
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	private Map<Object, Object> params = new HashMap<Object, Object>();
	
	public GetAttachments(Bug bug) {
		params.put("ids", bug.getID());
	}
	
	public GetAttachments(int id) {
		params.put("ids", id);
	}
	
	public List<Attachment> getAttachments() {
		List<Attachment> attachments = new ArrayList<Attachment>();
		
		if(hash.containsKey("bugs")) {
			AttachmentFactory factory = new AttachmentFactory();
			
			@SuppressWarnings("unchecked")
			Map<Object, Object> attachMap = (Map<Object, Object>)hash.get("bugs");
			Collection<Object> values = attachMap.values();
			for(Object obj : values) {
				Object[] arr = (Object[])obj;
				for(Object i : arr) {
					@SuppressWarnings("unchecked")
					Map<Object, Object> attachment = (Map<Object, Object>)i;
					
					Attachment a = factory.newAttachment()
							.setID((Integer)attachment.get("id"))
							.setBugID((Integer)attachment.get("bug_id"))
							.setData((String)attachment.get("data"))
							.setName((String)attachment.get("file_name"))
							.setSummary((String)attachment.get("summary"))
							.setCreator((String)attachment.get("creator"))
							.setMime((String)attachment.get("content_type"))
							.createAttachment();
					attachments.add(a);
				}
			}
		}
		
		return attachments;
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
