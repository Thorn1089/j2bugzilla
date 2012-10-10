package com.j2bugzilla.base;

/**
 * The {@code Flag} class represents a custom flag in a Bugzilla installation. A {@code Flag} may have one
 * of several states: {@code '+', '-', '?' or ' '}. The meaning of a {@code Flag} is context-dependent.
 * 
 * @author tom
 *
 */
public class Flag {

	private final String name;
	
	Flag(String name) {
		this.name = name;
	}
	
}
