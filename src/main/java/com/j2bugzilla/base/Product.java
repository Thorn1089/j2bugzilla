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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@code Product} object represents a product category on the Bugzilla installation. Each product
 * has a unique ID, and each {@link Bug} is associated with exactly one {@code Product}.
 * 
 * @author Tom
 *
 */
public class Product {

	private final int id;
	
	private final String name;
	
	private String description;
	
	private List<ProductVersion> versions = new ArrayList<ProductVersion>();
	
	/**
	 * Creates a new {@link Product} object with the specified unique ID and name.
	 * @param id An {@code integer} representing the unique product ID.
	 * @param name A {@code String} representing the unique product name.
	 */
	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Creates a new {@link Product} to be submitted to the Bugzilla installation. Useful when creating a
	 * new product via the webservice.
	 * @param name The name of the new product.
	 */
	public Product(String name) {
		this.id = -1;
		this.name = name;
	}
	
	/**
	 * Returns the ID of this {@link Product}. If the product was not retrieved from the webservice, returns -1.
	 * @return An integer representing the ID of this product.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the name of this {@link Product}.
	 * @return A {@code String} representing the product name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the description of this {@link Product}.
	 * @param description A {@code String} representing this product's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the description of this {@link Product}.
	 * @return A {@code String} describing this product.
	 */
	public String getDescription() {
		return description;
	}
	
	public void addProductVersion(ProductVersion version) {
		versions.add(version);
	}
	
	public List<ProductVersion> getProductVersions() {
		return Collections.unmodifiableList(versions);
	}
	
}
