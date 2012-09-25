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
 * <code>ConnectionException</code> is thrown any time there is an error trying to access
 * the Bugzilla installation over the network. It may be a network-related problem such as
 * a timeout or malformed URL, or it may be an underlying XML-RPC exception, but it
 * generally indicates that any further Bugzilla calls will fail.
 * 
 * ConnectionException will always be a wrapper for a nested <code>Exception</code> which
 * indicates the cause of the error.
 * @author Tom
 *
 */
public class ConnectionException extends Exception {

	/**
	 * Eclipse-generated SUID
	 */
	private static final long serialVersionUID = 2957676868743832929L;

	/**
	 * Public constructor which calls super()
	 * @param message A custom error message describing the issue
	 * @param cause The root cause of the exception
	 */
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
