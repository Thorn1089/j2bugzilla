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
 * A {@code ProductVersion} object represents a version of a product category on the Bugzilla installation. 
 * Each {@code ProductVersion} is associated with exactly one {@code Product} and contains a unique 
 * id within the product, a name, and other parameters.
 *
 */
public class ProductVersion {
	
	private final int id;
	
	private final String name;
	
	
	/**
	 * Creates a new {@link ProductVersion} object with the specified unique ID and name.
	 * @param id An {@code integer} representing the unique product version ID.
	 * @param name A {@code String} representing the unique product version name.
	 */
	public ProductVersion(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Returns the ID of this {@link ProductVersion}. If the product version id was not retrieved 
	 * from the Web service, returns -1.
	 * @return An integer representing the ID of this product.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the name of this {@link ProductVersion}.
	 * @return A {@code String} representing the product name.
	 */
	public String getName() {
		return name;
	}
}
