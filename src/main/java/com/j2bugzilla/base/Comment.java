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

/**
 * The {@code Comment} class represents a comment entered for a particular
 * {@link Bug} in a Bugzilla installation. Each comment has a unique ID, and
 * is correlated with at most one {@code Bug}.
 * 
 * @author tgolden
 *
 */
public class Comment {

	/**
	 * The unique ID of this {@link Comment} on a Bugzilla installation
	 */
	private final int id;
	
	/**
	 * The message content of this {@link Comment}
	 */
	private final String text;
	
	/**
	 * Creates a new {@link Comment} from a Bugzilla installation
	 * @param id The unique ID of this comment
	 * @param text The text content of this comment
	 */
	public Comment(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	/**
	 * Creates a new {@link Comment} to be submitted to a Bugzilla installation
	 * @param text The text content of this comment
	 */
	public Comment(String text) {
		this.id = -1;
		this.text = text;
	}
	
	/**
	 * @return The unique ID of this {@link Comment}
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return The text content of this {@link Comment}
	 */
	public String getText() {
		return text;
	}
	
	
}
