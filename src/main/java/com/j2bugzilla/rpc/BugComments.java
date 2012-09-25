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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Comment;

/**
 * This class allows clients to request a list of all public {@link Comment Comments} made on a 
 * specific {@link Bug} in a Bugzilla installation. The {@link Bug} must already
 * exist in the installation. 
 * 
 * @author Tom
 *
 */
public class BugComments implements BugzillaMethod {

	/**
	 * The XML-RPC method Bugzilla will use
	 */
	private static final String METHOD_NAME = "Bug.comments";
	
	/**
	 * A {@code Map} of parameter objects required by the XML-RPC method call.
	 */
	private final Map<Object, Object> params = new HashMap<Object, Object>();
	
	/**
	 * A {@code Map} of objects returned by the XML-RPC method call.
	 */
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	/**
	 * The ID of the {@link Bug}
	 */
	private final int id;
	
	/**
	 * Creates a new {@link BugComments} object for the specified
	 * {@link Bug}
	 * 
	 * @param bug A {@link Bug} to retrieve comments for
	 */
	public BugComments(Bug bug) {
		this(bug.getID());
	}
	
	/**
	 * Creates a new {@link BugComments} object for the specified
	 * {@link Bug} ID.
	 * @param id An integer specifying which {@link Bug} to retrieve
	 * comments for
	 */
	public BugComments(int id) {
		this.id = id;
		params.put("ids", id);
	}
	
	/**
	 * Returns a <code>List</code> of all public comments made on the
	 * {@link Bug} requested from the installation
	 * 
	 * @return A List of {@link Comment} objects representing user comments
	 */
	public List<Comment> getComments() {
		//List<String> commentList = new ArrayList<String>();
		List<Comment> commentList = new ArrayList<Comment>();
		
		if(hash.containsKey("bugs")) {
			/*
			 * Hideous, but it's the structure of the XML that
			 * Bugzilla returns. Since it's designed to return comments for
			 * multiple bugs at a time, there's extra nesting we don't need.
			 * TODO Allow requests for lists of Bugs
			 */
			@SuppressWarnings("unchecked")
			Map<String, Map<String, Map<Object, Object>[]>> m = 
				(Map<String, Map<String, Map<Object, Object>[]>>)hash.get("bugs");
			
			Map<String, Map<Object, Object>[]> weird = m.get(String.valueOf(id));
			Object[] comments = weird.get("comments");
			
			if(comments.length == 0) { return commentList; } //Early return to prevent ClassCast
			
			for(Object o : comments) {
				@SuppressWarnings("unchecked")
				Map<Object, Object> comment = (Map<Object, Object>)o;
				Comment c = new Comment((Integer)comment.get("id"), (String)comment.get("text"));
				commentList.add(c);
			}
		}
		
		return commentList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
