package com.j2bugzilla.base;

/**
 * The {@code Flag} class represents a custom flag in a Bugzilla installation. A {@code Flag} may have one
 * of several states: {@code '+', '-', '?' or ' '}. The meaning of a {@code Flag} is context-dependent.
 * 
 * @author tom
 */
public class Flag {

	/**
	 * The {@code Status} enum represents the current state of a {@link Flag}. It can represent one of four values.
	 * @author tom
	 */
	public enum Status { 
		/**
		 * The {@code UNSET} {@link Status} represents a {@link Flag} which has not been toggled. It is represented by ' '.
		 */
		UNSET, 
		
		/**
		 * The {@code POSITIVE} {@link Status} represents a {@link Flag} which has been toggled to '+'.
		 */
		POSITIVE, 
		
		/**
		 * The {@code NEGATIVE} {@link Status} represents a {@link Flag} which has been toggled to '-'.
		 */
		NEGATIVE, 
		
		/**
		 * The {@code UNKNOWN} {@link Status} represents a {@link Flag} which has been toggled to '?'.
		 */
		UNKNOWN 
	}
	
	private final String name;
	
	private final Status status;
	
	/**
	 * Creates a new {@link Flag} with the given name and status.
	 * @param name A unique name describing this {@code Flag}.
	 * @param status A {@link Status} describing the state of this {@code Flag}.
	 */
	Flag(String name, Status status) {
		this.name = name;
		this.status = status;
	}
	
	/**
	 * Returns the unique name for this {@link Flag}.
	 * @return A {@code String} representing the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the current state of this {@link Flag}.
	 * @return A {@link Status} enum representing the {@code Flag} state.
	 */
	public Status getStatus() {
		return status;
	}
	
}
