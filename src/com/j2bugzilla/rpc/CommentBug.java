package com.j2bugzilla.rpc;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Comment;


/**
 * The <code>CommentBug</code> class allows clients to add a new comment to an
 * existing {@link Bug} in a Bugzilla installation.
 * 
 * @author Tom
 *
 */
public class CommentBug implements BugzillaMethod {
	
	/**
	 * The method Bugzilla will execute via XML-RPC
	 */
	private static final String METHOD_NAME = "Bug.add_comment";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	/**
	 * Creates a new {@link CommentBug} object to add a comment to an
	 * existing {@link Bug} in the client's installation
	 * 
	 * @param bug A <code>Bug</code> to comment on
	 * @param comment The comment to append
	 */
	public CommentBug(Bug bug, String comment) {
		this(bug.getID(), comment);
	}
	
	/**
	 * Creates a new {@link CommentBug} object to add a {@link Comment}
	 * to an existing {@link Bug} in the client's installation
	 * 
	 * @param bug A {@code Bug} to comment on
	 * @param comment A {@code Comment} to append
	 */
	public CommentBug(Bug bug, Comment comment) {
		this(bug.getID(), comment.getText());
	}
	
	/**
	 * Creates a new {@link CommentBug} object to add a {@link Comment} to an existing
	 * {@link Bug} in the client's installation 
	 * @param id The integer ID of an existing {@code Bug}
	 * @param comment A {@code Comment} to append
	 */
	public CommentBug(int id, Comment comment) {
		this(id, comment.getText());
	}
	
	/**
	 * Creates a new {@link CommentBug} object to add a comment to an
	 * existing {@link Bug} in the client's installation
	 * 
	 * @param id The integer ID of an existing <code>Bug</code>
	 * @param comment The comment to append
	 */
	public CommentBug(int id, String comment) {
		params.put("id", id);
		params.put("comment", comment);
	}
	
	

	/**
	 * Returns the ID of the newly appended comment
	 * @return An <code>int</code> representing the ID of the new comment
	 */
	public int getCommentID() {
		if(hash.containsKey("id")) {
			Integer i = (Integer)hash.get("id");
			return i.intValue();
		} else {
			return -1;
		}
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
